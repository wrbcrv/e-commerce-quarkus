package dev.application.dto;

import java.time.LocalDate;

public record CartaoDTO(
    Integer idTipo,
    String numero,
    String cvv,
    LocalDate validade,
    String titular,
    String cpf) {
}