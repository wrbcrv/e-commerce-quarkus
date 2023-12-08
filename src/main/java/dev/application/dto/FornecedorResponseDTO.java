package dev.application.dto;

import java.util.List;

import dev.application.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String nome,
    List<EnderecoDTO> enderecos) {

  public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
    return new FornecedorResponseDTO(
        fornecedor.getId(),
        fornecedor.getNome(),
        fornecedor.getEnderecos().stream().map(enderecos -> EnderecoDTO.valueOf(enderecos)).toList());
  }
}
