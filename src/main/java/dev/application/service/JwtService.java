package dev.application.service;

import dev.application.dto.UsuarioResponseDTO;

public interface JwtService {

  public String generateJwt(UsuarioResponseDTO dto);
}