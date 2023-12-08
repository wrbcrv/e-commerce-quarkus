package dev.application.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import dev.application.dto.PedidoDTO;
import dev.application.dto.PedidoResponseDTO;
import dev.application.service.PedidoService;
import dev.application.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

  @Inject
  PedidoService service;

  @Inject
  UsuarioService usuarioService;

  @Inject
  JsonWebToken jwt;

  @POST
  @RolesAllowed({ "Admin", "User" })
  public Response insert(PedidoDTO dto) {
    String login = jwt.getSubject();
    PedidoResponseDTO retorno = service.insert(dto, login);

    return Response.status(201).entity(retorno).build();
  }

  @GET
  @RolesAllowed({ "Admin", "User" })
  public Response findAll() {
    return Response.ok(service.findAll()).build();
  }

  @GET
  @Path("/{id}")
  @RolesAllowed({ "Admin", "User" })
  public Response findById(@PathParam("id") Long id) {
    return Response.ok(service.findById(id)).build();
  }
}