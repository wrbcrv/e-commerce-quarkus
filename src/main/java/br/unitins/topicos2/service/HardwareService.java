package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.HardwareDTO;
import br.unitins.topicos2.dto.HardwareResponseDTO;

public interface HardwareService {

    List<HardwareResponseDTO> getAll(int page, int pageSize);

    HardwareResponseDTO findById(Long id);

    HardwareResponseDTO create(HardwareDTO dto);

    HardwareResponseDTO update(Long id, HardwareDTO dto);

    void delete(Long id);

    List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();
    
    long countByNome(String nome);
}