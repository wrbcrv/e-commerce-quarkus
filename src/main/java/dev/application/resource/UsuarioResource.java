package dev.application.resource;

import java.io.IOException;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import dev.application.application.Result;
import dev.application.dto.EnderecoDTO;
import dev.application.dto.UsuarioDTO;
import dev.application.dto.UsuarioResponseDTO;
import dev.application.form.ImageForm;
import dev.application.model.Perfil;
import dev.application.service.UsuarioService;
import dev.application.service.file.UsuarioFileService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
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

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

  @Inject
  UsuarioService service;

  @Inject
  UsuarioFileService usuarioFileService;

  private static final Logger LOG = Logger.getLogger(UsuarioResource.class);

  @POST
  public Response create(UsuarioDTO dto) {
    LOG.info("Cadastrando um novo usuário: " + dto.nome());

    UsuarioResponseDTO usuario = service.create(dto);

    LOG.infof("Usuário (%d) cadastrado com sucesso.", usuario.id());

    return Response.status(Status.CREATED).entity(usuario).build();
  }

  @PUT
  @Transactional
  @Path("/{id}")
  public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
    LOG.infof("Atualizando usuário com ID: %d", id);

    try {
      UsuarioResponseDTO usuario = service.update(dto, id);
      LOG.infof("Usuário com ID (%d) atualizado com sucesso.", id);

      return Response.ok(usuario).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao atualizar o usuário.");
      LOG.debug(e.getMessage());

      Result result = new Result(e.getConstraintViolations());

      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação ao atualizar o usuário com ID: " + id);
      LOG.debug(e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public void delete(@PathParam("id") Long id) {
    LOG.infof("Excluindo usuário com ID: %d", id);

    service.delete(id);
  }

  @GET
  public List<UsuarioResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando todos os usuários.");
    LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

    return service.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public UsuarioResponseDTO findById(@PathParam("id") Long id) {
    LOG.info("Buscando usuário por ID: " + id);

    return service.findById(id);
  }

  @POST
  @Path("/{id}/enderecos")
  @Transactional
  public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    LOG.infof("Associando endereços ao usuário com ID: %d", id);

    try {
      UsuarioResponseDTO usuarioAtualizado = service.createEnderecos(id, enderecosDTO);
      LOG.infof("Endereços associados ao usuário com ID: %d", id);

      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao associar endereços ao usuário.");
      LOG.debug(e.getMessage());

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Transactional
  @Path("/{id}/enderecos")
  public Response updateEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    LOG.infof("Atualizando endereços do usuário com ID: %d", id);

    try {
      UsuarioResponseDTO usuarioAtualizado = service.updateEnderecos(id, enderecosDTO);
      LOG.infof("Endereços do usuário com ID: %d atualizados com sucesso", id);

      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao atualizar os endereços do usuário.");
      LOG.debug(e.getMessage());

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{userId}/enderecos/{enderecoId}")
  @Transactional
  public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
    LOG.infof("Removendo endereço com ID: %d do usuário com ID: %d", enderecoId, userId);

    try {
      UsuarioResponseDTO usuarioAtualizado = service.removeEnderecos(userId, enderecoId);
      LOG.infof("Endereço com ID: %d removido do usuário com ID: %d", enderecoId, userId);

      return Response.ok(usuarioAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao remover endereço do usuário.");
      LOG.debug(e.getMessage());

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("/count")
  public long count() {
    LOG.info("Contando o número total de usuários.");

    return service.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    LOG.info("Contando o número de usuários com o nome: " + nome);

    return service.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<UsuarioResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando usuários com o nome: " + nome);
    LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

    return service.findByNome(nome, page, pageSize);
  }

  @GET
  @Path("/image/download/{imageName}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  public Response download(@PathParam("imageName") String imageName) {
    ResponseBuilder responseBuilder = Response.ok(usuarioFileService.download(imageName));
    responseBuilder.header("Content-Disposition", "attachment;filename=" + imageName);

    return responseBuilder.build();
  }

  @PATCH
  @Path("/image/upload")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response saveImage(@MultipartForm ImageForm imageForm) {
    try {
      usuarioFileService.save(imageForm.getId(), imageForm.getImageName(), imageForm.getImage());

      return Response.noContent().build();
    } catch (IOException e) {
      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.CONFLICT).entity(result).build();
    }
  }

  @GET
  @Path("/perfis")
  public Response getPerfis() {
    return Response.ok(Perfil.values()).build();
  }

  @GET
  @Path("/enderecos/{id}")
  public Response getEnderecos(@PathParam("id") Long id) {
    return Response.ok(service.getEnderecos(id)).build();
  }
}