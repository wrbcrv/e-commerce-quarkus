package dev.application.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CupomDTO(
        @NotBlank(message = "O campo descrição deve ser informado.")
        String descricao,

        @NotBlank(message = "O campo código deve ser informado.")
        String codigo,

        @NotNull(message = "O campo início deve ser informado.")
        LocalDate inicio,

        @NotNull(message = "O campo término deve ser informado.")
        LocalDate termino,

        @NotNull(message = "O campo desconto deve ser informado.")
        int desconto,

        List<HardwareDTO> hardwares) {
}