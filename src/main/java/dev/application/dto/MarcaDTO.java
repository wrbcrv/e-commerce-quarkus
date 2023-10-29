package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record MarcaDTO(
    @NotBlank(message = "O campo nome deve ser informado.") 
    String nome,

    @NotBlank(message = "O campo site deve ser informado.") 
    String site,
    
    LocalDate fundacao,

    @NotBlank(message = "O campo sede deve ser informado.") 
    String sede) {
}