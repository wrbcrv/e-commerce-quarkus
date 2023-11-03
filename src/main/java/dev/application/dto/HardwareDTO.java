package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareDTO(
        @NotNull(message = "O campo Marca deve ser informado.")
        Long idMarca,

        @NotBlank(message = "O campo Nome deve ser informado.")
        String nome,

        @NotNull(message = "O campo Preço deve ser informado.")
        Float preco,

        @NotNull(message = "O campo Estoque deve ser informado.")
        int estoque,

        @NotBlank(message = "O campo Modelo deve ser informado.")
        String modelo,

        @NotNull(message = "O campo Fabricante deve ser informado.")
        Long idFabricante,

        @NotNull(message = "O campo Lançamento deve ser informado.")
        LocalDate lancamento,
        
        @NotNull(message = "O campo Categoria deve ser informado.")
        Integer idCategoria,

        @NotNull(message = "O campo Status deve ser informado.")
        Integer idStatus,
        
        String imageName) {
}