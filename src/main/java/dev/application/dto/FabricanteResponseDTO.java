package dev.application.dto;

import dev.application.model.Fabricante;

public record FabricanteResponseDTO(
        Long id,
        String nome,
        String site
    ) {

    public static FabricanteResponseDTO valueOf(Fabricante fabricante) {
        return new FabricanteResponseDTO(
            fabricante.getId(), 
            fabricante.getNome(), 
            fabricante.getSite());
    }
}