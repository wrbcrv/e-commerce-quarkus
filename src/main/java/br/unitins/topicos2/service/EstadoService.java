package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EstadoDTO;
import br.unitins.topicos2.dto.EstadoResponseDTO;

public interface EstadoService {

        List<EstadoResponseDTO> getAll();

        EstadoResponseDTO findById(Long id);

        EstadoResponseDTO create(EstadoDTO dto);

        EstadoResponseDTO update(Long id, EstadoDTO dto);

        void delete(Long id);

        List<EstadoResponseDTO> findByNome(String nome);

        long count();
}