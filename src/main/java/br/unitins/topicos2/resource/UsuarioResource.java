package br.unitins.topicos2.resource;

import java.util.List;

import br.unitins.topicos2.application.Result;
import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.TelefoneDTO;
import br.unitins.topicos2.dto.UsuarioDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;
import br.unitins.topicos2.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService service;

    @POST
    public UsuarioResponseDTO insert(UsuarioDTO dto) {
        return service.insert(dto);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(UsuarioDTO dto, @PathParam("id") Long id) {
        try {
            UsuarioResponseDTO estado = service.update(dto, id);
            return Response.ok(estado).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        service.delete(id);
    }

    @GET
    public List<UsuarioResponseDTO> findAll() {
        return service.findByAll();
    }

    @GET
    @Path("/{id}")
    public UsuarioResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<UsuarioResponseDTO> findByNome(@PathParam("nome") String nome) {
        return service.findByNome(nome);
    }

    @POST
    @Path("/{id}/telefones")
    @Transactional
    public Response createTelefones(List<TelefoneDTO> telefonesDTO, @PathParam("id") Long id) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.createTelefones(id, telefonesDTO);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}/telefones")
    public Response updateTelefones(List<TelefoneDTO> telefonesDTO, @PathParam("id") Long id) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.updateTelefones(id, telefonesDTO);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{userId}/telefones/{telefoneId}")
    @Transactional
    public Response removeTelefone(@PathParam("userId") Long userId, @PathParam("telefoneId") Long telefoneId) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.removeTelefones(userId, telefoneId);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/enderecos")
    @Transactional
    public Response createEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.createEnderecos(id, enderecosDTO);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Transactional
    @Path("/{id}/enderecos")
    public Response updateEnderecos(List<EnderecoDTO> enderecosDTO, @PathParam("id") Long id) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.updateEnderecos(id, enderecosDTO);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{userId}/enderecos/{enderecoId}")
    @Transactional
    public Response removeEnderecos(@PathParam("userId") Long userId, @PathParam("enderecoId") Long enderecoId) {
        try {
            UsuarioResponseDTO usuarioAtualizado = service.removeEnderecos(userId, enderecoId);
            return Response.ok(usuarioAtualizado).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}