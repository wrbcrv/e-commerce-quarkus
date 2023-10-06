package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.FabricanteDTO;
import br.unitins.topicos2.dto.FabricanteResponseDTO;

public interface FabricanteService {

    List<FabricanteResponseDTO> getAll(int page, int pageSize);

    FabricanteResponseDTO findById(Long id);

    FabricanteResponseDTO create(FabricanteDTO dto);

    FabricanteResponseDTO update(Long id, FabricanteDTO dto);

    void delete(Long id);

    List<FabricanteResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
