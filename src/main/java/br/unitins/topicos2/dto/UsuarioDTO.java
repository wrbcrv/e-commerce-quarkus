package br.unitins.topicos2.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @NotBlank(message = "O campo deve ser informado.")
    String nome,
    @NotBlank(message = "O campo deve ser informado.")
    String login,
    @NotBlank(message = "O campo deve ser informado.")
    String senha,
    @NotBlank(message = "O campo deve ser informado.")
    String cpf,
    List<TelefoneDTO> telefones,
    List<EnderecoDTO> enderecos
    ) {
}