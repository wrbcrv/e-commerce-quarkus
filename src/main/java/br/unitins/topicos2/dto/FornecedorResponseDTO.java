package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        List<EnderecoDTO> enderecos
    ) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getEnderecos().stream().map(enderecos -> EnderecoDTO.valueOf(enderecos)).toList()
        );
    }
}
