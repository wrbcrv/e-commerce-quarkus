package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Fabricante;

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