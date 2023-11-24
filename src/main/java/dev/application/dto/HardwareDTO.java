package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HardwareDTO(
        @NotNull(message = "Marca é obrigatório")
        Long idMarca,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Preço é obrigatório")
        Float preco,

        @NotNull(message = "Estoque é obrigatório")
        int estoque,

        @NotBlank(message = "Modelo é obrigatório")
        String modelo,

        @NotNull(message = "Fabricante é obrigatório")
        Long idFabricante,

        @NotNull(message = "Lançamento é obrigatório")
        LocalDate lancamento,
        
        @NotNull(message = "Categoria é obrigatório")
        Integer idCategoria,

        @NotNull(message = "Status é obrigatório")
        Integer idStatus,
        
        String imageName) {
}