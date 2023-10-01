package br.unitins.topicos2.dto;

import java.time.format.DateTimeFormatter;

import br.unitins.topicos2.model.Cupom;

public record CupomResponseDTO(
        Long id,
        String descricao,
        String codigo,
        String inicio,
        String termino,
        int desconto) {

    public static CupomResponseDTO valueOf(Cupom cupom) {
        String formattedInicio = cupom.getInicio() != null
                ? cupom.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;
        String formattedTermino = cupom.getTermino() != null
                ? cupom.getTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        return new CupomResponseDTO(
                cupom.getId(),
                cupom.getDescricao(),
                cupom.getCodigo(),
                formattedInicio,
                formattedTermino,
                cupom.getDesconto());
    }
}