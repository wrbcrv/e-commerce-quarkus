package dev.application.service;

import java.util.List;

import dev.application.dto.MarcaDTO;
import dev.application.dto.MarcaResponseDTO;

public interface MarcaService {

    MarcaResponseDTO create(MarcaDTO dto);

    MarcaResponseDTO update(Long id, MarcaDTO dto);

    void delete(Long id);

    List<MarcaResponseDTO> findAll(int page, int pageSize);

    MarcaResponseDTO findById(Long id);

    List<MarcaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}