package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoDTO(
        @NotBlank(message = "O campo nome deve ser informado.") 
        String nome,
        @NotBlank(message = "O campo sigla deve ser informado.") 
        String sigla
        ) {
}
