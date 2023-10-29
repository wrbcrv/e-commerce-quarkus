package dev.application.service;

import java.util.List;

import dev.application.dto.EstadoDTO;
import dev.application.dto.EstadoResponseDTO;

public interface EstadoService {

        EstadoResponseDTO create(EstadoDTO dto);

        EstadoResponseDTO update(Long id, EstadoDTO dto);

        void delete(Long id);

        List<EstadoResponseDTO> findAll(int page, int pageSize);

        EstadoResponseDTO findById(Long id);

        List<EstadoResponseDTO> findByNome(String nome, int page, int pageSize);

        long count();

        long countByNome(String nome);
}