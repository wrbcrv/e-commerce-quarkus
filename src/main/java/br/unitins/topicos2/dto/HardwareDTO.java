package br.unitins.topicos2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareDTO(
        @NotNull (message = "O campo idMarca deve ser informado.")
        Long idMarca,
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,
        @NotNull (message = "O campo preço deve ser informado.")
        Float preco,
        @NotNull (message = "O campo estoque deve ser informado.")
        int estoque,
        @NotBlank(message = "O campo modelo deve ser informado.")
        String modelo,
        @NotNull (message = "O campo idFabricante deve ser informado.")
        Long idFabricante,
        @NotNull (message = "O campo lançamento deve ser informado.")
        LocalDate lancamento,
        
        Integer idCategoria,

        Integer idIntegridade
        ) {
}