package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

import dev.application.application.Result;
import dev.application.dto.CidadeDTO;
import dev.application.dto.CidadeResponseDTO;
import dev.application.service.CidadeService;
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

@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService cidadeService;

    private static final Logger LOG = Logger.getLogger(CidadeResource.class);

    @POST
    public Response create(CidadeDTO dto) {
        LOG.infof("Cadastrando uma nova cidade: %s", dto.nome());
        
        Result result = null;
        
        try {
            CidadeResponseDTO cidade = cidadeService.create(dto);
            LOG.infof("Cidade (%d) cadastrada com sucesso.", cidade.id());

            return Response.status(Status.CREATED).entity(cidade).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao cadastrar uma cidade.");
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
    public Response update(@PathParam("id") Long id, CidadeDTO dto) {
        LOG.infof("Atualizando cidade com ID: %d", id);

        try {
            CidadeResponseDTO cidade = cidadeService.update(id, dto);
            LOG.infof("Cidade com ID (%d) atualizada com sucesso.", id);

            return Response.ok(cidade).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao atualizar a cidade.");
            LOG.debug(e.getMessage());

            Result result = new Result(e.getConstraintViolations());

            return Response.status(Status.NOT_FOUND).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificação ao atualizar a cidade: " + e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Excluindo cidade com ID: %d", id);

        try {
            cidadeService.delete(id);
            LOG.infof("Cidade com ID (%d) excluída com sucesso.", id);

            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOG.fatal("Erro ao excluir a cidade com ID: " + id);
            LOG.debug(e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    public List<CidadeResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todas as cidades.");
        LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

        return cidadeService.findAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public CidadeResponseDTO findById(@PathParam("id") Long id) {
        LOG.info("Buscando cidade por ID: " + id);

        return cidadeService.findById(id);
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Contando o número total de cidades.");

        return cidadeService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        LOG.info("Contando o número de cidades com o nome: " + nome);

        return cidadeService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<CidadeResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando cidades com o nome: " + nome);
        LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

        return cidadeService.findByNome(nome, page, pageSize);
    }
}