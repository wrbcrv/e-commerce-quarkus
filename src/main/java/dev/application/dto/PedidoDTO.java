package dev.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
    List<ItemDTO> itens,

    @NotNull(message = "Endereço é obrigatório")
    Long idEndereco) {
}