package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

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

  private static final Logger LOG = Logger.getLogger(DescricaoResource.class);

  @POST
  public Response create(DescricaoDTO dto) {
    LOG.infof("Cadastrando uma nova descricao: %s", dto.conteudo());

    Result result = null;

    try {
      DescricaoResponseDTO descricao = descricaoService.create(dto);
      LOG.infof("Descrição (%d) cadastrada com sucesso.", descricao.id());

      return Response.status(Status.CREATED).entity(descricao).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao cadastrar uma descricao.");
      LOG.debug(e.getMessage());
      result = new Result(e.getConstraintViolations());
    } catch (Exception e) {
      LOG.fatal("Erro sem identificacao: " + e.getMessage());
      result = new Result(e.getMessage(), false);
    }

    return Response.status(Status.NOT_FOUND).entity(result).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") Long id, DescricaoDTO dto) {
    LOG.infof("Atualizando descrição com ID: %d", id);

    try {
      DescricaoResponseDTO descricao = descricaoService.update(id, dto);
      LOG.infof("Descrição com ID (%d) atualizada com sucesso.", id);

      return Response.ok(descricao).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao atualizar a descrição.");
      LOG.debug(e.getMessage());

      Result result = new Result(e.getConstraintViolations());

      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação ao atualizar a descrição: " + e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    LOG.infof("Excluindo descrição com ID: %d", id);

    try {
      descricaoService.delete(id);
      LOG.infof("Descrição com ID (%d) excluída com sucesso.", id);

      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      LOG.fatal("Erro ao excluir a descrição com ID: " + id);
      LOG.debug(e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<DescricaoResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando todas as descrições.");
    LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

    return descricaoService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public DescricaoResponseDTO findById(@PathParam("id") Long id) {
    LOG.info("Buscando descrição por ID: " + id);

    return descricaoService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    LOG.info("Contando o número total de descrições.");

    return descricaoService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    LOG.info("Contando o número de descrições com o nome: " + nome);

    return descricaoService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<DescricaoResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando descrições com o nome: " + nome);
    LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

    return descricaoService.findByConteudo(nome, page, pageSize);
  }
}