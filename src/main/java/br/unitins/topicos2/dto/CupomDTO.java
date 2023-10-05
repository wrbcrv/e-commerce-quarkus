package br.unitins.topicos2.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CupomDTO(
        @NotBlank(message = "O campo deve ser informado.")
        String descricao,
        @NotBlank(message = "O campo deve ser informado.")
        String codigo,
        @NotNull (message = "O campo deve ser informado.")
        LocalDate inicio,
        @NotNull (message = "O campo deve ser informado.")
        LocalDate termino,
        @NotNull (message = "O campo deve ser informado.")
        int desconto,

        List<HardwareDTO> hardwares) {
}