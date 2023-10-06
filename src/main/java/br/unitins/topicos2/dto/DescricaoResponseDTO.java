package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Descricao;

public record DescricaoResponseDTO(
        Long id,
        HardwareResponseDTO hardware,
        String conteudo
    ) {

    public static DescricaoResponseDTO valueOf(Descricao descricao) {
        return new DescricaoResponseDTO(
            descricao.getId(),
            HardwareResponseDTO.valueOf(descricao.getHardware()),
            descricao.getConteudo());
    }
}