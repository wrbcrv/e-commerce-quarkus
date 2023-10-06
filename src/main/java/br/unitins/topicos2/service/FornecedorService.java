package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.FornecedorDTO;
import br.unitins.topicos2.dto.FornecedorResponseDTO;

public interface FornecedorService {

        List<FornecedorResponseDTO> getAll(int page, int pageSize);

        FornecedorResponseDTO findById(Long id);

        FornecedorResponseDTO create(FornecedorDTO dto);

        FornecedorResponseDTO update(Long id, FornecedorDTO dto);

        FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

        FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

        FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId);

        void delete(Long id);

        List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize);

        long count();

        long countByNome(String nome);

        FornecedorResponseDTO associarHardware(Long fornecedorId, Long hardwareId);
}