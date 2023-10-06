package br.unitins.topicos2.resource;

import java.util.List;

import org.jboss.logging.Logger;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;
import br.unitins.topicos2.service.FornecedorService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/fornecedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    FornecedorService fornecedorService;

    private static final Logger LOG = Logger.getLogger(FornecedorResource.class);

    @GET
    public List<FornecedorResponseDTO> getAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        LOG.info("Buscando todos os fornecedores.");
        LOG.debug("ERRO DE DEBUG.");

        return fornecedorService.getAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public FornecedorResponseDTO findById(@PathParam("id") Long id) {
        return fornecedorService.findById(id);
    }

    @POST
    public Response insert(FornecedorDTO dto) {
        LOG.infof("Inserindo um fornecedor: %s", dto.nome());
        Result result = null;
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.create(dto);
            LOG.infof("Fornecedor (%d) criado com sucesso.", fornecedor.id());
            return Response.status(Status.CREATED).entity(fornecedor).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao incluir um fornecedor.");
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
    public Response update(@PathParam("id") Long id, FornecedorDTO dto) {
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.update(id, dto);
            return Response.ok(fornecedor).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());

            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    @Path("/{id}/enderecos")
    @Transactional
    public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
        try {
            FornecedorResponseDTO fornecedorAtualizado = fornecedorService.createEnderecos(id, enderecosDTO);
            return Response.ok(fornecedorAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}/enderecos")
    public Response updateEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
        try {
            FornecedorResponseDTO fornecedorAtualizado = fornecedorService.updateEnderecos(id, enderecosDTO);
            return Response.ok(fornecedorAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{userId}/enderecos/{enderecoId}")
    @Transactional
    public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
        try {
            FornecedorResponseDTO fornecedorAtualizado = fornecedorService.removeEnderecos(userId, enderecoId);
            return Response.ok(fornecedorAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/count")
    public long count() {
        return fornecedorService.count();
    }

    @GET
    @Path("/search/{nome}/count")
    public long count(@PathParam("nome") String nome) {
        return fornecedorService.countByNome(nome);
    }

    @GET
    @Path("/search/{nome}")
    public List<FornecedorResponseDTO> search(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {

        return fornecedorService.findByNome(nome, page, pageSize);
    }

    @POST
    @Path("/{fornecedorId}/associar-hardware/{hardwareId}")
    public Response associarHardware(
            @PathParam("fornecedorId") Long fornecedorId,
            @PathParam("hardwareId") Long hardwareId
        ) {

        FornecedorResponseDTO hardware = fornecedorService.associarHardware(fornecedorId, hardwareId);
        
        return Response.ok(hardware).build();
    }
}