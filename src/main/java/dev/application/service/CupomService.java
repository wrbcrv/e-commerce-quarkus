package dev.application.service;

import java.util.List;

import dev.application.dto.CupomDTO;
import dev.application.dto.CupomResponseDTO;

public interface CupomService {

        CupomResponseDTO create(CupomDTO dto);

        CupomResponseDTO update(Long id, CupomDTO dto);

        void delete(Long id);

        List<CupomResponseDTO> findAll(int page, int pageSize);

        CupomResponseDTO findById(Long id);

        List<CupomResponseDTO> findByCodigo(String codigo, int page, int pageSize);

        long count();

        long countByCodigo(String codigo);

        CupomResponseDTO associateHardware(Long cupomId, Long hardwareId);
}