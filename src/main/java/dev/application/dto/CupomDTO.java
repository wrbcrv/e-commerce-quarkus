package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CupomDTO(
        @NotBlank(message = "O campo Descrição deve ser informado.")
        String descricao,

        @NotBlank(message = "O campo Código deve ser informado.")
        String codigo,

        @NotNull(message = "O campo Início deve ser informado.")
        LocalDate inicio,

        @NotNull(message = "O campo Término deve ser informado.")
        LocalDate termino,

        @NotNull(message = "O campo Desconto deve ser informado.")
        int desconto) {
}