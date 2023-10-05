package br.unitins.topicos2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareDTO(
        @NotNull (message = "O campo deve ser informado.")
        Long idMarca,
        @NotBlank(message = "O campo deve ser informado.")
        String nome,
        @NotNull (message = "O campo deve ser informado.")
        Float preco,
        @NotNull (message = "O campo deve ser informado.")
        int estoque,
        @NotBlank(message = "O campo deve ser informado.")
        String modelo,
        @NotNull (message = "O campo deve ser informado.")
        LocalDate lancamento,
        @NotNull (message = "O campo deve ser informado.")
        Integer idCategoria,
        @NotNull (message = "O campo deve ser informado.")
        Integer idIntegridade
        ) {
}