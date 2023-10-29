package dev.application.service;

import java.util.List;

import dev.application.dto.FabricanteDTO;
import dev.application.dto.FabricanteResponseDTO;

public interface FabricanteService {

    FabricanteResponseDTO create(FabricanteDTO dto);

    FabricanteResponseDTO update(Long id, FabricanteDTO dto);

    void delete(Long id);

    List<FabricanteResponseDTO> findAll(int page, int pageSize);

    FabricanteResponseDTO findById(Long id);

    List<FabricanteResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
