package dev.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "O campo login não pode estar em branco.")
        String email,

        @NotBlank(message = "O campo senha não pode estar em branco.")
        String senha) {
}