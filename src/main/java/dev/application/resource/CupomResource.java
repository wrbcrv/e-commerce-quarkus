package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

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

  private static final Logger LOG = Logger.getLogger(CupomResource.class);

  @POST
  public Response create(CupomDTO dto) {
    LOG.infof("Cadastrando um novo cupom: %s", dto.codigo());

    Result result = null;

    try {
      CupomResponseDTO cupom = cupomService.create(dto);
      LOG.infof("Cupom (%d) cadastrado com sucesso.", cupom.id());

      return Response.status(Status.CREATED).entity(cupom).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao cadastrar um cupom.");
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
  public Response update(@PathParam("id") Long id, CupomDTO dto) {
    LOG.infof("Atualizando cupom com ID: %d", id);

    try {
      CupomResponseDTO cupom = cupomService.update(id, dto);
      LOG.infof("Cupom com ID (%d) atualizado com sucesso.", id);

      return Response.ok(cupom).build();
    } catch (ConstraintViolationException e) {
      LOG.error("Erro ao atualizar o cupom.");
      LOG.debug(e.getMessage());

      Result result = new Result(e.getConstraintViolations());

      return Response.status(Status.NOT_FOUND).entity(result).build();
    } catch (Exception e) {
      LOG.fatal("Erro sem identificação ao atualizar o cupom: " + e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    LOG.infof("Excluindo cupom com ID: %d", id);

    try {
      cupomService.delete(id);
      LOG.infof("Cupom com ID (%d) excluído com sucesso.", id);

      return Response.status(Status.NO_CONTENT).build();
    } catch (Exception e) {
      LOG.fatal("Erro ao excluir o cupom com ID: " + id);
      LOG.debug(e.getMessage());

      Result result = new Result(e.getMessage(), false);

      return Response.status(Status.NOT_FOUND).entity(result).build();
    }
  }

  @GET
  public List<CupomResponseDTO> findAll(
      @QueryParam("page") @DefaultValue("0") int page,
      @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

    LOG.info("Buscando todas os cupons.");
    LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

    return cupomService.findAll(page, pageSize);
  }

  @GET
  @Path("/{id}")
  public CupomResponseDTO findById(@PathParam("id") Long id) {
    LOG.info("Buscando cupom por ID: " + id);

    return cupomService.findById(id);
  }

  @GET
  @Path("/count")
  public long count() {
    LOG.info("Contando o número total de cupons.");

    return cupomService.count();
  }

  @GET
  @Path("/search/{codigo}")
  public CupomResponseDTO search(@PathParam("codigo") String codigo) {
    return cupomService.findByCodigo(codigo);
  }
}