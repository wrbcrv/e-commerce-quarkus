package dev.application.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dev.application.application.Result;
import dev.application.dto.HardwareDTO;
import dev.application.dto.HardwareResponseDTO;
import dev.application.form.HardwareImageForm;
import dev.application.model.Categoria;
import dev.application.service.HardwareService;
import dev.application.service.file.HardwareFileService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/hardwares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HardwareResource {

  @Inject
  HardwareService hardwareService;

  @Inject
  HardwareFileService fileService;

  @POST
  public Response create(HardwareDTO dto) {
    HardwareResponseDTO hardware = hardwareService.create(dto);
    return Response.status(Status.CREATED).entity(hardware).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, HardwareDTO dto) {
    try {
      HardwareResponseDTO hardware = hardwareService.update(id, dto);
      return Response.ok(hardware).build();
    } catch (ConstraintViolationException e) {
      Result result = new Result(e.getConstraintViolations());
      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    try {
      hardwareService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<HardwareResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return hardwareService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public HardwareResponseDTO findById(@PathParam("id") Long id) {
    return hardwareService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    return hardwareService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return hardwareService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<HardwareResponseDTO> searchByNome(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return hardwareService.findByNome(nome, page, pageSize);
  }

  @GET
  @Path("/search/{modelo}")
  public List<HardwareResponseDTO> searchByModelo(
      @PathParam("modelo") String modelo,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return hardwareService.findByModelo(modelo, page, pageSize);
  }

  @GET
  @Path("/image/download/{imageName}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response download(@PathParam("imageName") String imageName) {
    ResponseBuilder responseBuilder = Response.ok(fileService.download(imageName));
    responseBuilder.header("Content-Disposition", "attachment;filename=" + imageName);

    return responseBuilder.build();
  }

  @PATCH
  @Path("/image/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response saveImages(@MultipartForm HardwareImageForm imageForm) {
    try {
      fileService.save(imageForm.getId(), imageForm.getImageName(), imageForm.getImage());
      return Response.noContent().build();
    } catch (IOException e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.CONFLICT).entity(result).build();
    }
  }

  @GET
  @Path("/categorias")
  public Response getCategorias() {
    return Response.ok(Categoria.values()).build();
  }

  @GET
  @Path("/status")
  public Response getStatus() {
    return Response.ok(dev.application.model.Status.values()).build();
  }

  @GET
  @Path("/relatorios")
  @Produces("application/pdf")
  public Response generatePdfReport(@QueryParam("filter") @DefaultValue("") String filter) {
    byte[] pdf = hardwareService.createPdfReports(filter);

    ResponseBuilder responseBuilder = Response.ok(pdf);
    responseBuilder.header("Content-Disposition", "attachment;filename=relatorio.pdf");

    return responseBuilder.build();
  }
}
