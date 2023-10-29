package dev.application.dto;

import jakarta.validation.constraints.NotBlank;

public record EstadoDTO(
        @NotBlank(message = "O campo deve ser informado.") 
        String nome,

        @NotBlank(message = "O campo deve ser informado.") 
        String sigla) {
}