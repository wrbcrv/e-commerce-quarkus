package dev.application.service;

import dev.application.model.Usuario;

public interface JwtService {
    
    public String generateJwt(Usuario usuario);
}