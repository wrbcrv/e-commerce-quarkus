package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record CupomDTO(
        @NotBlank(message = "O campo Descrição deve ser informado.")
        String descricao,

        @NotBlank(message = "O campo Código deve ser informado.")
        String codigo,

        @NotBlank(message = "O campo Início deve ser informado.")
        LocalDate inicio,

        @NotBlank(message = "O campo Término deve ser informado.")
        LocalDate termino,

        @NotBlank(message = "O campo Desconto deve ser informado.")
        int desconto) {
}