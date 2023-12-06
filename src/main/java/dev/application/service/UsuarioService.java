package dev.application.service;

import java.util.List;

import dev.application.dto.EnderecoDTO;
import dev.application.dto.UsuarioDTO;
import dev.application.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO create(@Valid UsuarioDTO dto);

    UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    void delete(Long id);

    List<UsuarioResponseDTO> findAll(int page, int pageSize);

    UsuarioResponseDTO findById(Long id);
    
    UsuarioResponseDTO findByLogin(String login);

    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize);

    UsuarioResponseDTO saveImage(Long id, String imageName);

    EnderecoDTO findEnderecoByUsuarioId(Long usuarioId, Long enderecoId);

    UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO);

    UsuarioResponseDTO updateEndereco(Long usuarioId, Long enderecoId, EnderecoDTO enderecosDTO);

    UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId);

    long count();

    long countByNome(String nome);
}