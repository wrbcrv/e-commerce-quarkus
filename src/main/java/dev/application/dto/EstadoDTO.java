package dev.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
        @NotBlank(message = "O campo Nome deve ser informado.")
        @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
        String nome,

        @NotBlank(message = "O campo Sigla deve ser informado.") 
        @Size(min = 2, max = 2, message = "O campo sigla deve possuir 2 caracteres.")
        String sigla) {
}