package dev.application.service;

import java.util.List;

import dev.application.dto.PedidoDTO;
import dev.application.dto.PedidoResponseDTO;

public interface PedidoService {
  
  public PedidoResponseDTO insert(PedidoDTO dto, String login);

  public PedidoResponseDTO findById(Long id);

  public List<PedidoResponseDTO> findAll();

  public List<PedidoResponseDTO> findAll(String login);
}