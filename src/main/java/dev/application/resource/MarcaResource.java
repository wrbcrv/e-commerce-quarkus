package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

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

  private static final Logger LOG = Logger.getLogger(MarcaResource.class);

  @POST
  public Response create(MarcaDTO dto) {
    LOG.infof("Inserindo uma marca: %s", dto.nome());

    Result result = null;

    try {
      MarcaResponseDTO marca = marcaService.create(dto);
      LOG.infof("Marca (%d) criada com sucesso.", marca.id());

      return Response.status(Status.CREATED).entity(marca).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao incluir uma marca.");
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
  public Response update(@PathParam("id") Long id, MarcaDTO dto) {
    LOG.infof("Atualizando marca com ID: %d", id);

    try {
      MarcaResponseDTO marca = marcaService.update(id, dto);
      LOG.infof("Marca com ID (%d) atualizada com sucesso.", id);

      return Response.ok(marca).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao atualizar a marca.");
      LOG.debug(e.getMessage());

      Result result = new Result(e.getConstraintViolations());

      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação ao atualizar a marca: " + e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    LOG.infof("Excluindo marca com ID: %d", id);

    try {
      marcaService.delete(id);
      LOG.infof("Marca com ID (%d) excluída com sucesso.", id);

      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      LOG.fatal("Erro ao excluir a marca com ID: " + id);
      LOG.debug(e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<MarcaResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando todas as marcas.");
    LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

    return marcaService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public MarcaResponseDTO findById(@PathParam("id") Long id) {
    LOG.info("Buscando marca por ID: " + id);

    return marcaService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    LOG.info("Contando o número total de marcas.");

    return marcaService.count();
  }

  @GET
  @Path("/search/{nome}/count")
  public long count(@PathParam("nome") String nome) {
    LOG.info("Contando o número de marcas com o nome: " + nome);

    return marcaService.countByNome(nome);
  }

  @GET
  @Path("/search/{nome}")
  public List<MarcaResponseDTO> search(
      @PathParam("nome") String nome,
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando marcas com o nome: " + nome);
    LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

    return marcaService.findByNome(nome, page, pageSize);
  }
}