package dev.application.dto;

import java.util.List;

import dev.application.model.Perfil;
import dev.application.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String rg,
        String login,
        String senha,
        List<TelefoneDTO> telefones,
        List<EnderecoDTO> enderecos,
        Perfil perfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        if (usuario == null)
            return null;

        List<TelefoneDTO> telefoneDTOs = null;
        if (usuario.getTelefones() != null && !usuario.getTelefones().isEmpty())
            telefoneDTOs = usuario.getTelefones().stream()
                    .map(telefones -> TelefoneDTO.valueOf(telefones))
                    .toList();

        List<EnderecoDTO> enderecoDTOs = null;
        if (usuario.getEnderecos() != null && !usuario.getEnderecos().isEmpty())
            enderecoDTOs = usuario.getEnderecos().stream()
                    .map(enderecos -> EnderecoDTO.valueOf(enderecos))
                    .toList();

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getCpf(),
                usuario.getRg(),
                usuario.getLogin(),
                usuario.getSenha(),
                telefoneDTOs,
                enderecoDTOs,
                usuario.getPerfil());
    }
}