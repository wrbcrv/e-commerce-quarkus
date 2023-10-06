package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.HardwareDTO;
import br.unitins.topicos2.dto.HardwareResponseDTO;
import br.unitins.topicos2.service.HardwareService;
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

@Path("/hardwares")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HardwareResource {

    @Inject
    HardwareService hardwareService;

    private static final Logger LOG = Logger.getLogger(HardwareResource.class);

    @GET
    public List<HardwareResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {

        LOG.info("Buscando todos os hardwares.");
        LOG.debug("ERRO DE DEBUG.");

        return hardwareService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public HardwareResponseDTO findById(@PathParam("id") Long id) {
        return hardwareService.findById(id);
    }

    @POST
    public Response insert(HardwareDTO dto) {
        LOG.infof("Inserindo um hardware: %s", dto.nome());
        Result result = null;
        try {
            HardwareResponseDTO hardware = hardwareService.create(dto);
            LOG.infof("Hardware (%d) criado com sucesso.", hardware.id());
            return Response.status(Status.CREATED).entity(hardware).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um hardware.");
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
    public Response update(@PathParam("id") Long id, HardwareDTO dto) {
        try {
            HardwareResponseDTO hardware = hardwareService.update(id, dto);
            return Response.ok(hardware).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        hardwareService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return hardwareService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return hardwareService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<HardwareResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {

        return hardwareService.findByNome(nome, page, pageSize);
    }
}