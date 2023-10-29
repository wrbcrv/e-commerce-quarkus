package dev.application.service;

import java.util.List;

import dev.application.dto.EnderecoDTO;
import dev.application.dto.FornecedorDTO;
import dev.application.dto.FornecedorResponseDTO;

public interface FornecedorService {

        FornecedorResponseDTO create(FornecedorDTO dto);

        FornecedorResponseDTO update(Long id, FornecedorDTO dto);

        void delete(Long id);

        List<FornecedorResponseDTO> findAll(int page, int pageSize);

        FornecedorResponseDTO findById(Long id);

        List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize);

        FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

        FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

        FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId);

        long count();

        long countByNome(String nome);

        FornecedorResponseDTO associateHardware(Long fornecedorId, Long hardwareId);
}