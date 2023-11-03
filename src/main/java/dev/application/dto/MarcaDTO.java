package dev.application.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MarcaDTO(
    @NotBlank(message = "O campo Nome deve ser informado.") 
    String nome,

    @NotBlank(message = "O campo Site deve ser informado.") 
    String site,
    
    @NotNull(message = "O campo Fundação deve ser informado.")
    LocalDate fundacao,

    @NotBlank(message = "O campo Sede deve ser informado.") 
    String sede) {
}