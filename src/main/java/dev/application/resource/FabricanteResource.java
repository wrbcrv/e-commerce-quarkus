package dev.application.resource;

import java.util.List;

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

    @POST
    public Response create(FabricanteDTO dto) {
        Result result = null;

        try {
            FabricanteResponseDTO fabricante = fabricanteService.create(dto);
            return Response.status(Status.CREATED).entity(fabricante).build();
        } catch (ConstraintViolationException e) {
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            result = new Result(e.getMessage(), false);
        }

        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, FabricanteDTO dto) {
        try {
            FabricanteResponseDTO fabricante = fabricanteService.update(id, dto);
            return Response.ok(fabricante).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage(), false);
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            fabricanteService.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage(), false);
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    public List<FabricanteResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return fabricanteService.findAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public FabricanteResponseDTO findById(@PathParam("id") Long id) {
        return fabricanteService.findById(id);
    }

    @GET
    @Path("/count")
    public long count() {
        return fabricanteService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return fabricanteService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<FabricanteResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return fabricanteService.findByNome(nome, page, pageSize);
    }
}
