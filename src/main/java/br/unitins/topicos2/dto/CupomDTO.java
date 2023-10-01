package br.unitins.topicos2.dto;

import java.time.LocalDate;

public record CupomDTO(
        String descricao,
        String codigo,
        LocalDate inicio,
        LocalDate termino,
        int desconto) {
}