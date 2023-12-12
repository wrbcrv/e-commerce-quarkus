package dev.application.resource;

import java.util.List;

import dev.application.application.Result;
import dev.application.dto.CartaoDTO;
import dev.application.dto.EnderecoDTO;
import dev.application.dto.HardwareResponseDTO;
import dev.application.dto.UsuarioDTO;
import dev.application.dto.UsuarioResponseDTO;
import dev.application.model.Perfil;
import dev.application.model.Tipo;
import dev.application.service.UsuarioService;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

  @Inject
  UsuarioService service;

  @POST
  public Response create(UsuarioDTO dto) {
    UsuarioResponseDTO usuario = service.create(dto);
    return Response.status(Status.CREATED).entity(usuario).build();
  }

  @PUT
  @Transactional
  @Path("/{id}")
  public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
    try {
      UsuarioResponseDTO usuario = service.update(dto, id);
      return Response.ok(usuario).build();
    } catch (ConstraintViolationException e) {
      Result result = new Result(e.getConstraintViolations());
      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      Result result = new Result(e.getMessage(), false);
      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void delete(@PathParam("id") Long id) {
    service.delete(id);
  }

  @GET
  public List<UsuarioResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return service.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public UsuarioResponseDTO findById(@PathParam("id") Long id) {
    return service.findById(id);
  }

  @POST
  @Path("/{id}/enderecos")
  @Transactional
  public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    try {
      UsuarioResponseDTO usuarioAtualizado = service.createEnderecos(id, enderecosDTO);
      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Transactional
  @Path("/{usuarioId}/enderecos/{enderecoId}")
  public Response updateEndereco(@PathParam("usuarioId") Long usuarioId, @PathParam("enderecoId") Long enderecoId,
      EnderecoDTO enderecoDTO) {
    try {
      UsuarioResponseDTO usuarioAtualizado = service.updateEndereco(usuarioId, enderecoId, enderecoDTO);
      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{userId}/enderecos/{enderecoId}")
  @Transactional
  public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
    try {
      UsuarioResponseDTO usuarioAtualizado = service.removeEnderecos(userId, enderecoId);
      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("{usuarioId}/cartoes/{cartaoId}")
  public Response getCartaoByUsuarioId(@PathParam("usuarioId") Long usuarioId,
      @PathParam("cartaoId") Long cartaoId) {
    return Response.ok(service.findCartaoByUsuarioId(usuarioId, cartaoId)).build();
  }

  @POST
  @Transactional
  @Path("/{usuarioId}/cartoes")
  public Response createCartao(@PathParam("usuarioId") Long usuarioId, List<CartaoDTO> cartao)
      throws ConstraintViolationException {
    UsuarioResponseDTO usuario = service.createCartao(usuarioId, cartao);

    return Response.ok(usuario).build();
  }

  @PUT
  @Transactional
  @Path("/{usuarioId}/cartoes/{cartaoId}")
  public Response updateCartao(@PathParam("usuarioId") Long usuarioId, @PathParam("cartaoId") Long cartaoId,
      CartaoDTO cartaoDTO) {
    UsuarioResponseDTO usuario = service.updateCartao(usuarioId, cartaoId, cartaoDTO);

    return Response.ok(usuario).build();
  }

  @DELETE
  @Transactional
  @Path("/{usuarioId}/cartoes/{cartaoId}")
  public Response deleteCartao(@PathParam("usuarioId") Long usuarioId, @PathParam("cartaoId") Long cartaoId) {
    UsuarioResponseDTO usuario = service.deleteCartao(usuarioId, cartaoId);

    return Response.ok(usuario).build();
  }

  @GET
  @Path("/count")
  public long count() {
    return service.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    return service.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<UsuarioResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
    return service.findByNome(nome, page, pageSize);
  }

  @GET
  @Path("/perfis")
  public Response getPerfis() {
    return Response.ok(Perfil.values()).build();
  }

  @GET
  @Path("/tipos")
  public Response getTipos() {
    return Response.ok(Tipo.values()).build();
  }

  @GET
  @Path("{usuarioId}/enderecos/{enderecoId}")
  public Response getEnderecoByUsuarioId(@PathParam("usuarioId") Long usuarioId,
      @PathParam("enderecoId") Long enderecoId) {
    return Response.ok(service.findEnderecoByUsuarioId(usuarioId, enderecoId)).build();
  }

  @PUT
  @Transactional
  @Path("/{usuarioId}/favoritos/{hardwareId}")
  public Response addFavorito(@PathParam("usuarioId") Long usuarioId, @PathParam("hardwareId") Long hardwareId) {
    try {
      UsuarioResponseDTO usuario = service.addFavorito(usuarioId, hardwareId);
      return Response.ok(usuario).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @GET
  @Transactional
  @Path("/{usuarioId}/favoritos")
  public Response getFavoritos(@PathParam("usuarioId") Long usuarioId) {
    try {
      List<HardwareResponseDTO> favoritos = service.getFavoritos(usuarioId);
      return Response.ok(favoritos).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Transactional
  @Path("/{usuarioId}/favoritos/{hardwareId}")
  public Response deleteFavorito(@PathParam("usuarioId") Long usuarioId, @PathParam("hardwareId") Long hardwareId) {
    try {
      UsuarioResponseDTO usuario = service.deleteFavorito(usuarioId, hardwareId);
      return Response.ok(usuario).build();
    } catch (NotFoundException e) {
      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }
}