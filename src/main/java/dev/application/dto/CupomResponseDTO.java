package dev.application.dto;

import java.time.LocalDate;
import java.util.List;

import dev.application.model.Cupom;

public record CupomResponseDTO(
    Long id,
    String descricao,
    String codigo,
    LocalDate inicio,
    LocalDate termino,
    int desconto) {

  public static CupomResponseDTO valueOf(Cupom cupom) {


    return new CupomResponseDTO(
        cupom.getId(),
        cupom.getDescricao(),
        cupom.getCodigo(),
        cupom.getInicio(),
        cupom.getTermino(),
        cupom.getDesconto());
  }

  public static List<CupomResponseDTO> valueOf(List<Cupom> item) {
    return item.stream().map(itens -> CupomResponseDTO.valueOf(itens)).toList();
  }
}