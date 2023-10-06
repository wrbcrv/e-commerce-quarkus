package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Descricao;

public record DescricaoResponseDTO(
        Long id,
        String conteudo
    ) {

    public static DescricaoResponseDTO valueOf(Descricao descricao) {
        return new DescricaoResponseDTO(
            descricao.getId(), 
            descricao.getConteudo());
    }
}