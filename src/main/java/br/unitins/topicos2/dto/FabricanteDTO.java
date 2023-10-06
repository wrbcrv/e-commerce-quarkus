package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record FabricanteDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,
        @NotBlank(message = "O campo site deve ser informado.")
        String site
    ) {
}