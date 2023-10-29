package dev.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DescricaoDTO(
        @NotNull (message = "O campo idHardware deve ser informado.")
        Long idHardware,
        
        @NotBlank(message = "O campo conteúdo deve ser informado.")
        String conteudo) { 
}
