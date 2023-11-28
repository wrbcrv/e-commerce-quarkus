package dev.application.resource;

import dev.application.dto.AuthDTO;
import dev.application.dto.UsuarioResponseDTO;
import dev.application.service.HashService;
import dev.application.service.JwtService;
import dev.application.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

  @Inject
  UsuarioService service;

  @Inject
  HashService hashService;

  @Inject
  JwtService jwtService;

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response login(@Valid AuthDTO authDTO) {
    String hash = hashService.getHashSenha(authDTO.senha());

    UsuarioResponseDTO usuario = service.findByLoginAndSenha(authDTO.login(), hash);

    if (usuario == null)
      return Response.status(Status.NOT_FOUND)
          .entity("Usuário não encontrado.")
          .build();

    return Response.ok()
        .header("Authorization", jwtService.generateJwt(usuario))
        .build();
  }
}