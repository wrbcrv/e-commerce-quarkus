package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.MarcaDTO;
import br.unitins.topicos2.dto.MarcaResponseDTO;
import br.unitins.topicos2.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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

    @GET
    public List<MarcaResponseDTO> getAll() {
        LOG.info("Buscando todas as marcas.");
        LOG.debug("ERRO DE DEBUG.");
        return marcaService.getAll();
    }

    @GET
    @Path("/{id}")
    public MarcaResponseDTO findById(@PathParam("id") Long id) {
        return marcaService.findById(id);
    }

    @POST
    public Response insert(MarcaDTO dto) {
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
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            result = new Result(e.getMessage(), false);
        }
        return Response.status(Status.NOT_FOUND).entity(result).build();

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MarcaDTO dto) {
        try {
            MarcaResponseDTO marca = marcaService.update(id, dto);
            return Response.ok(marca).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        marcaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return marcaService.count();
    }

    @GET
    @Path("/search/{nome}")
    public List<MarcaResponseDTO> search(@PathParam("nome") String nome) {
        return marcaService.findByNome(nome);
    }
}