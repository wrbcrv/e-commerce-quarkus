package dev.application.service;

import java.util.List;

import dev.application.dto.HardwareDTO;
import dev.application.dto.HardwareResponseDTO;
import jakarta.validation.Valid;

public interface HardwareService {

    HardwareResponseDTO create(@Valid HardwareDTO dto);

    HardwareResponseDTO update(Long id, HardwareDTO dto);

    void delete(Long id);

    List<HardwareResponseDTO> findAll(int page, int pageSize);

    HardwareResponseDTO findById(Long id);

    List<HardwareResponseDTO> findByNome(String nome, int page, int pageSize);

    List<HardwareResponseDTO> findByModelo(String modelo, int page, int pageSize);

    HardwareResponseDTO saveImage(Long id, String imageName);

    long count();

    long countByNome(String nome);

    public byte[] createPdfReports(String filter);
}