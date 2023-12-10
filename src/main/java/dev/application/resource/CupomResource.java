package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.CupomDTO;
import dev.application.dto.CupomResponseDTO;
import dev.application.service.CupomService;
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

@Path("/cupons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CupomResource {

  @Inject
  CupomService cupomService;

  @POST
  public Response create(CupomDTO dto) {
    Result result = null;

    try {
      CupomResponseDTO cupom = cupomService.create(dto);
      return Response.status(Status.CREATED).entity(cupom).build();
    } catch (ConstraintViolationException e) {
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, CupomDTO dto) {
    try {
      CupomResponseDTO cupom = cupomService.update(id, dto);
      return Response.ok(cupom).build();
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
      cupomService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<CupomResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return cupomService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public CupomResponseDTO findById(@PathParam("id") Long id) {
    return cupomService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    return cupomService.count();
  }

  @GET
  @Path("/search/{codigo}")
  public CupomResponseDTO search(@PathParam("codigo") String codigo) {
    return cupomService.findByCodigo(codigo);
  }
}