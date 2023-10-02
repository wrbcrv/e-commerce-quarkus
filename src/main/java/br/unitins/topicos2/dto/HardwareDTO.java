package br.unitins.topicos2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareDTO(
        @NotBlank(message = "O campo deve ser informado.")
        String nome,
        @NotNull
        Float preco,
        @NotNull
        int estoque,
        @NotBlank(message = "O campo deve ser informado.")
        String modelo,
        @NotNull
        LocalDate lancamento) {
}