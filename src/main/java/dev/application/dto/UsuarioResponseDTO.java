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
    List<EnderecoDTO> enderecos,
    Perfil perfil,
    String imageName) {

  public static UsuarioResponseDTO valueOf(Usuario usuario) {
    if (usuario == null)
      return null;

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
        enderecoDTOs,
        usuario.getPerfil(),
        usuario.getImageName());
  }
}