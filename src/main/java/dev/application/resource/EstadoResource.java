package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.EstadoDTO;
import dev.application.dto.EstadoResponseDTO;
import dev.application.service.EstadoService;
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

@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {

  @Inject
  EstadoService estadoService;

  @POST
  public Response create(EstadoDTO dto) {
    EstadoResponseDTO estado = estadoService.create(dto);
    return Response.status(Status.CREATED).entity(estado).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, EstadoDTO dto) {
    try {
      EstadoResponseDTO estado = estadoService.update(id, dto);
      return Response.ok(estado).build();
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
      estadoService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<EstadoResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return estadoService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public EstadoResponseDTO findById(@PathParam("id") Long id) {
    return estadoService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    return estadoService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return estadoService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<EstadoResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return estadoService.findByNome(nome, page, pageSize);
  }
}