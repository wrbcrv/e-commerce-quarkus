package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.EstadoDTO;
import br.unitins.topicos2.dto.EstadoResponseDTO;
import br.unitins.topicos2.service.EstadoService;
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

    @GET
    public List<EstadoResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {

        LOG.info("Buscando todos os estados.");
        LOG.debug("ERRO DE DEBUG.");

        return estadoService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public EstadoResponseDTO findById(@PathParam("id") Long id) {
        return estadoService.findById(id);
    }

    @POST
    public Response insert(EstadoDTO dto) {
        LOG.infof("Inserindo um estado: %s", dto.nome());
        Result result = null;
        try {
            EstadoResponseDTO estado = estadoService.create(dto);
            LOG.infof("Estado (%d) criado com sucesso.", estado.id());
            return Response.status(Status.CREATED).entity(estado).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um estado.");
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
        try {
            EstadoResponseDTO estado = estadoService.update(id, dto);
            return Response.ok(estado).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        estadoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return estadoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return estadoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<EstadoResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {

        return estadoService.findByNome(nome, page, pageSize);
    }
}