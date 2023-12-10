package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.MarcaDTO;
import dev.application.dto.MarcaResponseDTO;
import dev.application.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarcaResource {

  @Inject
  MarcaService marcaService;

  @POST
  public Response create(MarcaDTO dto) {
    Result result = null;

    try {
      MarcaResponseDTO marca = marcaService.create(dto);
      return Response.status(Status.CREATED).entity(marca).build();
    } catch (ConstraintViolationException e) {
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, MarcaDTO dto) {
    try {
      MarcaResponseDTO marca = marcaService.update(id, dto);
      return Response.ok(marca).build();
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
      marcaService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<MarcaResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return marcaService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public MarcaResponseDTO findById(@PathParam("id") Long id) {
    return marcaService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    return marcaService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return marcaService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<MarcaResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return marcaService.findByNome(nome, page, pageSize);
  }
}