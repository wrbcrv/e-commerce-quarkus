package dev.application.resource;

import java.util.List;

import org.jboss.logging.Logger;

import dev.application.application.Result;
import dev.application.dto.EstadoDTO;
import dev.application.dto.EstadoResponseDTO;
import dev.application.service.EstadoService;
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

@Path("/estados")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EstadoResource {

    @Inject
    EstadoService estadoService;

    private static final Logger LOG = Logger.getLogger(EstadoResource.class);

    @POST
    public Response create(EstadoDTO dto) {
        LOG.infof("Cadastrando um novo estado: %s", dto.nome());

        Result result = null;
        
        try {
            EstadoResponseDTO estado = estadoService.create(dto);
            LOG.infof("Estado (%d) cadastrado com sucesso.", estado.id());

            return Response.status(Status.CREATED).entity(estado).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao cadastrar um estado.");
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
    public Response update(@PathParam("id") Long id, EstadoDTO dto) {
        LOG.infof("Atualizando estado com ID: %d", id);

        try {
            EstadoResponseDTO estado = estadoService.update(id, dto);
            LOG.infof("Estado com ID (%d) atualizado com sucesso.", id);

            return Response.ok(estado).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao atualizar o estado.");
            LOG.debug(e.getMessage());

            Result result = new Result(e.getConstraintViolations());

            return Response.status(Status.NOT_FOUND).entity(result).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificação ao atualizar o estado: " + e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Excluindo estado com ID: %d", id);

        try {
            estadoService.delete(id);
            LOG.infof("Estado com ID (%d) excluído com sucesso.", id);

            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOG.fatal("Erro ao excluir o estado com ID: " + id);
            LOG.debug(e.getMessage());

            Result result = new Result(e.getMessage(), false);

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    public List<EstadoResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os estados.");
        LOG.debug("Método findAll chamado com page=" + page + " e pageSize=" + pageSize);

        return estadoService.findAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public EstadoResponseDTO findById(@PathParam("id") Long id) {
        LOG.info("Buscando estado por ID: " + id);

        return estadoService.findById(id);
    }

    @GET
    @Path("/count")
    public long count() {
        LOG.info("Contando o número total de estados.");

        return estadoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        LOG.info("Contando o número de estados com o nome: " + nome);

        return estadoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<EstadoResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando estados com o nome: " + nome);
        LOG.debug("Método search chamado com nome=" + nome + ", page=" + page + " e pageSize=" + pageSize);

        return estadoService.findByNome(nome, page, pageSize);
    }
}