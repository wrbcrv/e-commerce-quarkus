package dev.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,
        
        @NotNull (message = "Informe o(s) endereço(s)")
        List<EnderecoDTO> enderecos,
        
        List<HardwareDTO> hardwares) {    
}