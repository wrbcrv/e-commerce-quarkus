package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CidadeDTO(
        @NotBlank(message = "O campo deve ser informado.") 
        String nome,
        
        @NotNull(message = "O campo deve ser informado.")
        Long idEstado
        ) {
}
