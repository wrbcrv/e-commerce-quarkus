package dev.application.service;

import java.util.List;

import dev.application.dto.CidadeDTO;
import dev.application.dto.CidadeResponseDTO;

public interface CidadeService {

        CidadeResponseDTO create(CidadeDTO dto);

        CidadeResponseDTO update(Long id, CidadeDTO dto);

        void delete(Long id);
        
        List<CidadeResponseDTO> findAll(int page, int pageSize);

        CidadeResponseDTO findById(Long id);

        List<CidadeResponseDTO> findByNome(String nome, int page, int pageSize);

        long count();

        long countByNome(String nome);
}