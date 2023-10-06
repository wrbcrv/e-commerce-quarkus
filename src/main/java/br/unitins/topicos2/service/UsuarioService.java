package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.TelefoneDTO;
import br.unitins.topicos2.dto.UsuarioDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;

public interface UsuarioService {

    List<UsuarioResponseDTO> getAll(int page, int pageSize);

    UsuarioResponseDTO findById(Long id);

    UsuarioResponseDTO insert(UsuarioDTO dto);

    UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    void delete(Long id);

    UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO);

    UsuarioResponseDTO updateTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO);

    UsuarioResponseDTO removeTelefones(Long usuarioId, Long telefoneId);

    UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO);

    UsuarioResponseDTO updateEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO);

    UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId);

    List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}