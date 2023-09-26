package br.unitins.topicos2.service;

import java.util.List;

import br.unitins.topicos2.dto.EnderecoDTO;
import br.unitins.topicos2.dto.TelefoneDTO;
import br.unitins.topicos2.dto.UsuarioDTO;
import br.unitins.topicos2.dto.UsuarioResponseDTO;

public interface UsuarioService {

    public UsuarioResponseDTO insert(UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public List<UsuarioResponseDTO> findByAll();

    public UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO);

    public UsuarioResponseDTO updateTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO);

    public UsuarioResponseDTO removeTelefones(Long usuarioId, Long telefoneId);

    public UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO);

    public UsuarioResponseDTO updateEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO);

    public UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId);
}