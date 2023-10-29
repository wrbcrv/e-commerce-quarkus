package dev.application.service;

import java.util.List;

import dev.application.dto.HardwareDTO;
import dev.application.dto.HardwareResponseDTO;

public interface HardwareService {

    HardwareResponseDTO create(HardwareDTO dto);

    HardwareResponseDTO update(Long id, HardwareDTO dto);

    void delete(Long id);

    List<HardwareResponseDTO> findAll(int page, int pageSize);

    HardwareResponseDTO findById(Long id);

    List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}