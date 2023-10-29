package dev.application.dto;

import java.time.format.DateTimeFormatter;

import dev.application.model.Marca;

public record MarcaResponseDTO(
        Long id,
        String nome,
        String site,
        String fundacao,
        String sede) {

    public static MarcaResponseDTO valueOf(Marca marca) {
        String formattedFundacao = marca.getFundacao() != null
                ? marca.getFundacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        return new MarcaResponseDTO(
                marca.getId(),
                marca.getNome(),
                marca.getSite(),
                formattedFundacao,
                marca.getSede());
    }
}