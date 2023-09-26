package br.unitins.topicos2.dto;

import java.util.List;

import br.unitins.topicos2.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String login,
        String senha,
        String cpf,
        List<TelefoneDTO> telefones,
        List<EnderecoDTO> enderecos) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getCpf(),
                usuario.getTelefones().stream().map(telefones -> TelefoneDTO.valueOf(telefones)).toList(),
                usuario.getEnderecos().stream().map(enderecos -> EnderecoDTO.valueOf(enderecos)).toList());
    }
}