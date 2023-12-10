package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.EnderecoDTO;
import dev.application.dto.FornecedorDTO;
import dev.application.dto.FornecedorResponseDTO;
import dev.application.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/fornecedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {

  @Inject
  FornecedorService fornecedorService;

  @POST
  public Response create(FornecedorDTO dto) {
    Result result = null;

    try {
      FornecedorResponseDTO fornecedor = fornecedorService.create(dto);
      return Response.status(Status.CREATED).entity(fornecedor).build();
    } catch (ConstraintViolationException e) {
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, FornecedorDTO dto) {
    try {
      FornecedorResponseDTO fornecedor = fornecedorService.update(id, dto);
      return Response.ok(fornecedor).build();
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
      fornecedorService.delete(id);
      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<FornecedorResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return fornecedorService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public FornecedorResponseDTO findById(@PathParam("id") Long id) {
    return fornecedorService.findById(id);
  }

  @POST
  @Path("/{id}/enderecos")
  @Transactional
  public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.createEnderecos(id, enderecosDTO);
      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Transactional
  @Path("/{id}/enderecos")
  public Response updateEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.updateEnderecos(id, enderecosDTO);
      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{userId}/enderecos/{enderecoId}")
  @Transactional
  public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.removeEnderecos(userId, enderecoId);
      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("/count")
  public long count() {
    return fornecedorService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return fornecedorService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<FornecedorResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return fornecedorService.findByNome(nome, page, pageSize);
  }
}
