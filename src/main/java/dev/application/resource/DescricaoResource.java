package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.DescricaoDTO;
import dev.application.dto.DescricaoResponseDTO;
import dev.application.service.DescricaoService;
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

@Path("/descricoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DescricaoResource {

  @Inject
  DescricaoService descricaoService;

  @POST
  public Response create(DescricaoDTO dto) {
    Result result = null;

    try {
      DescricaoResponseDTO descricao = descricaoService.create(dto);
      return Response.status(Status.CREATED).entity(descricao).build();
    } catch (ConstraintViolationException e) {
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, DescricaoDTO dto) {
    try {
      DescricaoResponseDTO descricao = descricaoService.update(id, dto);
      return Response.ok(descricao).build();
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
      descricaoService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<DescricaoResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return descricaoService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public DescricaoResponseDTO findById(@PathParam("id") Long id) {
    return descricaoService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    return descricaoService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return descricaoService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<DescricaoResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return descricaoService.findByConteudo(nome, page, pageSize);
  }
}
