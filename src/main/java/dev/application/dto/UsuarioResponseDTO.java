package dev.application.dto;

import java.util.List;
import java.util.Set;

import dev.application.model.Perfil;
import dev.application.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String email,
        String senha,
        String nome,
        String cpf,
        List<TelefoneDTO> telefones,
        List<EnderecoDTO> enderecos,
        Set<Perfil> perfis) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getTelefones().stream().map(telefones -> TelefoneDTO.valueOf(telefones)).toList(),
                usuario.getEnderecos().stream().map(enderecos -> EnderecoDTO.valueOf(enderecos)).toList(),
                usuario.getPerfis());
    }
}