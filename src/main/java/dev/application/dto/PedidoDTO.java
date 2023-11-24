package dev.application.dto;

import java.util.List;

public record PedidoDTO(
    List<ItemDTO> itens) {
}