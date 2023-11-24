package dev.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dev.application.dto.ItemDTO;
import dev.application.dto.PedidoDTO;
import dev.application.dto.PedidoResponseDTO;
import dev.application.model.Hardware;
import dev.application.model.Item;
import dev.application.model.Pedido;
import dev.application.repository.HardwareRepository;
import dev.application.repository.PedidoRepository;
import dev.application.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

  @Inject
  HardwareRepository hardwareRepository;

  @Inject
  UsuarioRepository usuarioRepository;

  @Inject
  PedidoRepository pedidoRepository;

  @Override
  @Transactional
  public PedidoResponseDTO insert(PedidoDTO pedidoDTO, String login) {
    Pedido pedido = new Pedido();

    pedido.setData(LocalDateTime.now());

    Double total = 0.0;

    for (ItemDTO itemDto : pedidoDTO.itens())
      total += (itemDto.preco() * itemDto.quantidade());
    pedido.setTotal(total);

    pedido.setItens(new ArrayList<Item>());
    for (ItemDTO itemDto : pedidoDTO.itens()) {
      Item item = new Item();
      item.setPreco(itemDto.preco());
      item.setQuantidade(itemDto.quantidade());
      item.setPedido(pedido);
      Hardware hardware = hardwareRepository.findById(itemDto.idHardware());
      item.setHardware(hardware);

      hardware.setEstoque(hardware.getEstoque() - item.getQuantidade());

      pedido.getItens().add(item);
    }

    pedido.setUsuario(usuarioRepository.findByLogin(login));

    pedidoRepository.persist(pedido);

    return PedidoResponseDTO.valueOf(pedido);

  }

  @Override
  public PedidoResponseDTO findById(Long id) {
    return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
  }

  @Override
  public List<PedidoResponseDTO> findByAll() {
    return pedidoRepository.listAll().stream().map(e -> PedidoResponseDTO.valueOf(e)).toList();
  }

  @Override
  public List<PedidoResponseDTO> findByAll(String login) {
    return pedidoRepository.listAll().stream().map(e -> PedidoResponseDTO.valueOf(e)).toList();
  }
}