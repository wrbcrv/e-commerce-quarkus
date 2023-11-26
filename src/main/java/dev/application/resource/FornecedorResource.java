package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

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

  private static final Logger LOG = Logger.getLogger(FornecedorResource.class);

  @POST
  public Response create(FornecedorDTO dto) {
    LOG.infof("Cadastrando um novo fornecedor: %s", dto.nome());

    Result result = null;

    try {
      FornecedorResponseDTO fornecedor = fornecedorService.create(dto);
      LOG.infof("Fornecedor (%d) cadastrado com sucesso.", fornecedor.id());

      return Response.status(Status.CREATED).entity(fornecedor).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao cadastrar um fornecedor.");
      LOG.debug(e.getMessage());
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação: " + e.getMessage());
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, FornecedorDTO dto) {
    LOG.infof("Atualizando fornecedor com ID: %d", id);

    try {
      FornecedorResponseDTO fornecedor = fornecedorService.update(id, dto);
      LOG.infof("Fornecedor com ID (%d) atualizado com sucesso.", id);

      return Response.ok(fornecedor).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao atualizar o fornecedor.");
      LOG.debug(e.getMessage());

      Result result = new Result(e.getConstraintViolations());

      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação ao atualizar o fornecedor: " + e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    LOG.infof("Excluindo fornecedor com ID: %d", id);

    try {
      fornecedorService.delete(id);
      LOG.infof("Fornecedor com ID (%d) excluído com sucesso.", id);

      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      LOG.fatal("Erro ao excluir o fornecedor com ID: " + id);
      LOG.debug(e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<FornecedorResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando todos os fornecedores.");
    LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

    return fornecedorService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public FornecedorResponseDTO findById(@PathParam("id") Long id) {
    LOG.info("Buscando fornecedor por ID: " + id);

    return fornecedorService.findById(id);
  }

  @POST
  @Path("/{id}/enderecos")
  @Transactional
  public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    LOG.info("Inserindo endereços para fornecedor com ID: " + id);

    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.createEnderecos(id, enderecosDTO);
      LOG.info("Endereços inseridos com sucesso para o fornecedor com ID: " + id);

      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao inserir endereços para o fornecedor com ID: " + id);

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Transactional
  @Path("/{id}/enderecos")
  public Response updateEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
    LOG.info("Atualizando endereços para fornecedor com ID: " + id);

    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.updateEnderecos(id, enderecosDTO);
      LOG.info("Endereços atualizados com sucesso para o fornecedor com ID: " + id);

      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao atualizar endereços para o fornecedor com ID: " + id);

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @DELETE
  @Path("/{userId}/enderecos/{enderecoId}")
  @Transactional
  public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
    LOG.info("Removendo endereço com ID " + enderecoId + " do fornecedor com ID: " + userId);

    try {
      FornecedorResponseDTO fornecedorAtualizado = fornecedorService.removeEnderecos(userId, enderecoId);
      LOG.info("Endereço removido com sucesso do fornecedor com ID: " + userId);

      return Response.ok(fornecedorAtualizado).build();
    } catch (NotFoundException e) {
      LOG.error("Erro ao remover endereço do fornecedor com ID: " + userId);

      return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("/count")
  public long count() {
    LOG.info("Contando o número total de fornecedores.");

    return fornecedorService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    LOG.info("Contando o número de fornecedores com o nome: " + nome);

    return fornecedorService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<FornecedorResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando fornecedores com o nome: " + nome);
    LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

    return fornecedorService.findByNome(nome, page, pageSize);
  }

  @POST
  @Path("/{fornecedorId}/associate-hardware/{hardwareId}")
  public Response associateHardware(
      @PathParam("fornecedorId") Long fornecedorId,
      @PathParam("hardwareId") Long hardwareId) {

    LOG.infof("Associando hardware com (ID: %d) ao fornecedor (ID: %d)" + hardwareId, fornecedorId);

    FornecedorResponseDTO hardware = fornecedorService.associateHardware(fornecedorId, hardwareId);

    LOG.infof("Hardware (ID: %d) associado com sucesso ao fornecedor (ID: %d): " + fornecedorId, hardwareId);

    return Response.ok(hardware).build();
  }
}