package dev.application.service;

import java.util.List;

import dev.application.dto.DescricaoDTO;
import dev.application.dto.DescricaoResponseDTO;

public interface DescricaoService {

        DescricaoResponseDTO create(DescricaoDTO dto);

        DescricaoResponseDTO update(Long id, DescricaoDTO dto);

        void delete(Long id);

        List<DescricaoResponseDTO> findAll(int page, int pageSize);

        DescricaoResponseDTO findById(Long id);

        List<DescricaoResponseDTO> findByConteudo(String conteudo, int page, int pageSize);

        long count();

        long countByNome(String conteudo);
}