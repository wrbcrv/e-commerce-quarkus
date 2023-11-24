package dev.application.dto;

import java.util.List;

import dev.application.model.Item;

public record ItemResponseDTO(
    Integer quantidade,
    Double preco,
    Long idHardware,
    String nome) {

  public static ItemResponseDTO valueOf(Item item) {
    return new ItemResponseDTO(
        item.getQuantidade(),
        item.getPreco(),
        item.getHardware().getId(),
        item.getHardware().getNome());
  }

  public static List<ItemResponseDTO> valueOf(List<Item> item) {
    return item.stream().map(itens -> ItemResponseDTO.valueOf(itens)).toList();
  }
}