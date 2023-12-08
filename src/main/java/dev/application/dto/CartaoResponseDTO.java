package dev.application.dto;

import java.time.LocalDate;

import dev.application.model.Cartao;
import dev.application.model.Tipo;

public record CartaoResponseDTO(
    Long id,
    Tipo tipo,
    String numero,
    String cvv,
    LocalDate validade,
    String titular,
    String cpf) {

  public static CartaoResponseDTO valueOf(Cartao cartao) {
    return new CartaoResponseDTO(
        cartao.getId(),
        cartao.getTipo(),
        cartao.getNumero(),
        cartao.getCvv(),
        cartao.getValidade(),
        cartao.getTitular(),
        cartao.getCpf());
  }
}