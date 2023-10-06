package br.unitins.topicos2.dto;

import jakarta.validation.constraints.NotBlank;

public record DescricaoDTO(
        @NotBlank(message = "O campo conteúdo deve ser informado.")
        String conteudo
    ) { 
}
