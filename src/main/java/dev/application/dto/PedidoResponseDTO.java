package dev.application.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;

import dev.application.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    String data,
    UsuarioResponseDTO usuario,
    Double total,
    List<ItemResponseDTO> itens,
    EnderecoDTO endereco,
    CartaoResponseDTO cartao,
    List<CupomResponseDTO> cupons) {

  public static PedidoResponseDTO valueOf(Pedido pedido) {
    String formattedData = pedido.getData() != null
        ? pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        : null;

    return new PedidoResponseDTO(
        pedido.getId(),
        formattedData,
        UsuarioResponseDTO.valueOf(pedido.getUsuario()),
        pedido.getTotal(),
        ItemResponseDTO.valueOf(pedido.getItens()),
        EnderecoDTO.valueOf(pedido.getEndereco()),
        CartaoResponseDTO.valueOf(pedido.getCartao()),
        CupomResponseDTO.valueOf(pedido.getCupom()));
  }
}