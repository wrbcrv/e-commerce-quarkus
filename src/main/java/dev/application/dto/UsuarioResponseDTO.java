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
    List<CartaoResponseDTO> cartoes,
    Perfil perfil,
    List<HardwareResponseDTO> favoritos,
    String imageName) {

  public static UsuarioResponseDTO valueOf(Usuario usuario) {
    if (usuario == null)
      return null;

    List<EnderecoDTO> enderecos = null;

    if (usuario.getEnderecos() != null && !usuario.getEnderecos().isEmpty())
      enderecos = usuario.getEnderecos().stream().map(e -> EnderecoDTO.valueOf(e)).toList();

    List<CartaoResponseDTO> cartoes = null;

    if (usuario.getCartoes() != null && !usuario.getCartoes().isEmpty())
      cartoes = usuario.getCartoes().stream().map(c -> CartaoResponseDTO.valueOf(c)).toList();

    List<HardwareResponseDTO> favoritos = null;

    if (usuario.getFavoritos() != null && !usuario.getFavoritos().isEmpty())
      favoritos = usuario.getFavoritos().stream().map(c -> HardwareResponseDTO.valueOf(c)).toList();

    return new UsuarioResponseDTO(
        usuario.getId(),
        usuario.getNome(),
        usuario.getSobrenome(),
        usuario.getCpf(),
        usuario.getRg(),
        usuario.getLogin(),
        usuario.getSenha(),
        enderecos,
        cartoes,
        usuario.getPerfil(),
        favoritos,
        usuario.getImageName());
  }
}