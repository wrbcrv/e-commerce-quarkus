package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.DescricaoDTO;
import br.unitins.topicos2.dto.DescricaoResponseDTO;

public interface DescricaoService {

        List<DescricaoResponseDTO> getAll(int page, int pageSize);

        DescricaoResponseDTO findById(Long id);

        DescricaoResponseDTO create(DescricaoDTO dto);

        DescricaoResponseDTO update(Long id, DescricaoDTO dto);

        void delete(Long id);

        List<DescricaoResponseDTO> findByConteudo(String conteudo, int page, int pageSize);

        long count();

        long countByNome(String conteudo);
}