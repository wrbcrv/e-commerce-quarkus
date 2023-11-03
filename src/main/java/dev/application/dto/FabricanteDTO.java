package dev.application.dto;

import jakarta.validation.constraints.NotBlank;

public record FabricanteDTO(
        @NotBlank(message = "O campo Nome deve ser informado.")
        String nome,

        @NotBlank(message = "O campo Site deve ser informado.")
        String site) {
}