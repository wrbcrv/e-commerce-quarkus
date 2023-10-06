package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.DescricaoDTO;
import br.unitins.topicos2.dto.DescricaoResponseDTO;
import br.unitins.topicos2.service.DescricaoService;
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

    @GET
    public List<DescricaoResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {
            
        LOG.info("Buscando todas os descrições.");
        LOG.debug("ERRO DE DEBUG.");

        return descricaoService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public DescricaoResponseDTO findById(@PathParam("id") Long id) {
        return descricaoService.findById(id);
    }

    @POST
    public Response insert(DescricaoDTO dto) {
        LOG.infof("Inserindo uma descricao: %s", dto.conteudo());
        Result result = null;
        try {
            DescricaoResponseDTO descricao = descricaoService.create(dto);
            LOG.infof("Descricao (%d) criada com sucesso.", descricao.id());
            return Response.status(Status.CREATED).entity(descricao).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir uma descricao.");
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
        try {
            DescricaoResponseDTO descricao = descricaoService.update(id, dto);
            return Response.ok(descricao).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        descricaoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public long count() {
        return descricaoService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return descricaoService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<DescricaoResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize
        ) {

        return descricaoService.findByConteudo(nome, page, pageSize);
    }
}