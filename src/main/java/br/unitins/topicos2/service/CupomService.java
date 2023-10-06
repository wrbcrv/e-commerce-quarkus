package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.CupomDTO;
import br.unitins.topicos2.dto.CupomResponseDTO;

public interface CupomService {

        List<CupomResponseDTO> getAll(int page, int pageSize);

        CupomResponseDTO findById(Long id);

        CupomResponseDTO create(CupomDTO dto);

        CupomResponseDTO update(Long id, CupomDTO dto);

        void delete(Long id);

        List<CupomResponseDTO> findByCodigo(String codigo, int page, int pageSize);

        long count();

        long countByCodigo(String codigo);

        CupomResponseDTO associarHardware(Long cupomId, Long hardwareId);
}