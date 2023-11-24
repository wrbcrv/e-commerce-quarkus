package dev.application.dto;

import java.time.LocalDateTime;
import java.util.List;

import dev.application.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    LocalDateTime data,
    UsuarioResponseDTO usuario,
    Double total,
    List<ItemResponseDTO> itens) {

  public static PedidoResponseDTO valueOf(Pedido pedido) {
    return new PedidoResponseDTO(
        pedido.getId(),
        pedido.getData(),
        UsuarioResponseDTO.valueOf(pedido.getUsuario()),
        pedido.getTotal(),
        ItemResponseDTO.valueOf(pedido.getItens()));
  }
}