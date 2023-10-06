package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.MarcaDTO;
import br.unitins.topicos2.dto.MarcaResponseDTO;

public interface MarcaService {

    List<MarcaResponseDTO> getAll(int page, int pageSize);

    MarcaResponseDTO findById(Long id);

    MarcaResponseDTO create(MarcaDTO dto);

    MarcaResponseDTO update(Long id, MarcaDTO dto);

    void delete(Long id);

    List<MarcaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}