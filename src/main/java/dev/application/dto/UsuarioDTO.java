package dev.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotBlank(message = "Sobrenome é obrigatório")
    String sobrenome,

    @NotBlank(message = "CPF é obrigatório")
    String cpf,
    
    @NotBlank(message = "RG é obrigatório")
    String rg,

    @NotBlank(message = "Login é obrigatório")
    String login,

    @NotBlank(message = "Senha é obrigatório")
    String senha,

    List<EnderecoDTO> enderecos,
    
    List<CartaoResponseDTO> cartoes,

    @NotNull(message = "Perfil é obrigatório.")
    Integer idPerfil,

    List<HardwareResponseDTO> favoritos,
    
    String imageName) {
}