package br.unitins.topicos2.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record MarcaDTO(
    @NotBlank(message = "O campo deve ser informado.") 
    String nome,
    @NotBlank(message = "O campo deve ser informado.") 
    String site,
    
    LocalDate fundacao,
    @NotBlank(message = "O campo deve ser informado.") 
    String sede
) {
}