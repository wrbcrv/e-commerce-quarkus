package dev.application.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import dev.application.dto.UsuarioResponseDTO;
import dev.application.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/logged")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoggedInUserResource {

  @Inject
  JsonWebToken jsonWebToken;

  @Inject
  UsuarioService usuarioService;

  @GET
  @RolesAllowed({ "Admin", "User" })
  public Response getUsuario() {
    String login = jsonWebToken.getSubject();

    UsuarioResponseDTO usuarioResponseDTO = usuarioService.findByLogin(login);

    return Response.ok(usuarioResponseDTO).build();
  }
}