package dev.application.dto;

import dev.application.model.Telefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneDTO(
        Long id,
        @NotBlank(message = "O campo DDD deve ser informado.")
        String ddd,
        
        @NotBlank(message = "O campo Número deve ser informado.")
        String numero) {

    public static TelefoneDTO valueOf(Telefone telefone) {
        return new TelefoneDTO(
            telefone.getId(),
            telefone.getDdd(),
            telefone.getNumero()
        );
    }
}