package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

import dev.application.application.Result;
import dev.application.dto.FabricanteDTO;
import dev.application.dto.FabricanteResponseDTO;
import dev.application.service.FabricanteService;
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

@Path("/fabricantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FabricanteResource {

    @Inject
    FabricanteService fabricanteService;

    private static final Logger LOG = Logger.getLogger(FabricanteResource.class);

    @POST
    public Response create(FabricanteDTO dto) {
        LOG.infof("Cadastrando um novo fabricante: %s", dto.nome());

        Result result = null;
        
        try {
            FabricanteResponseDTO fabricante = fabricanteService.create(dto);
            LOG.infof("Fabricante (%d) cadastrado com sucesso.", fabricante.id());

            return Response.status(Status.CREATED).entity(fabricante).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao cadastrar um fabricante.");
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
    public Response update(@PathParam("id") Long id, FabricanteDTO dto) {
        LOG.infof("Atualizando fabricante com ID: %d", id);

        try {
            FabricanteResponseDTO fabricante = fabricanteService.update(id, dto);
            LOG.infof("Fabricante com ID (%d) atualizado com sucesso.", id);

            return Response.ok(fabricante).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao atualizar o fabricante.");
            LOG.debug(e.getMessage());

            Result result = new Result(e.getConstraintViolations());

            return Response.status(Status.NOT_FOUND).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificação ao atualizar o fabricante: " + e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Excluindo fabricante com ID: %d", id);

        try {
            fabricanteService.delete(id);
            LOG.infof("Fabricante com ID (%d) excluído com sucesso.", id);

            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOG.fatal("Erro ao excluir o fabricante com ID: " + id);
            LOG.debug(e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    public List<FabricanteResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os fabricantes.");
        LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

        return fabricanteService.findAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public FabricanteResponseDTO findById(@PathParam("id") Long id) {
        LOG.info("Buscando fabricante por ID: " + id);

        return fabricanteService.findById(id);
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Contando o número total de fabricantes.");

        return fabricanteService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        LOG.info("Contando o número de fabricantes com o nome: " + nome);

        return fabricanteService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<FabricanteResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando fabricantes com o nome: " + nome);
        LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

        return fabricanteService.findByNome(nome, page, pageSize);
    }
}