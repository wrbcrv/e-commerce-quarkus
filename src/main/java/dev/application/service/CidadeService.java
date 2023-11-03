package dev.application.service;

import java.util.List;

import dev.application.dto.CidadeDTO;
import dev.application.dto.CidadeResponseDTO;
import jakarta.validation.Valid;

public interface CidadeService {

        CidadeResponseDTO create(@Valid CidadeDTO dto);

        CidadeResponseDTO update(Long id, CidadeDTO dto);

        void delete(Long id);
        
        List<CidadeResponseDTO> findAll(int page, int pageSize);

        CidadeResponseDTO findById(Long id);

        List<CidadeResponseDTO> findByNome(String nome, int page, int pageSize);

        long count();

        long countByNome(String nome);
}