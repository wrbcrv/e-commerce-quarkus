package dev.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DescricaoDTO(
        @NotNull (message = "O campo Hardware deve ser informado.")
        Long idHardware,
        
        @NotBlank(message = "O campo Conteúdo deve ser informado.")
        String conteudo) { 
}
