package br.unitins.topicos2.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FornecedorDTO(
        @NotBlank(message = "O campo nome deve ser informado.")
        String nome,
        @NotNull (message = "Informe o endereço")
        List<EnderecoDTO> enderecos
    ) {    
}