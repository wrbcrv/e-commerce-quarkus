package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CidadeDTO;
import br.unitins.topicos2.dto.CidadeResponseDTO;

public interface CidadeService {

        List<CidadeResponseDTO> getAll();

        CidadeResponseDTO findById(Long id);

        CidadeResponseDTO create(CidadeDTO dto);

        CidadeResponseDTO update(Long id, CidadeDTO dto);

        void delete(Long id);

        List<CidadeResponseDTO> findByNome(String nome);

        long count();
}