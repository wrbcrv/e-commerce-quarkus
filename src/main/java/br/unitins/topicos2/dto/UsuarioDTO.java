package br.unitins.topicos2.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
    @NotBlank(message = "O campo nome deve ser informado.")
    String nome,
    @NotBlank(message = "O campo sigla deve ser informado.")
    String login,
    @NotBlank(message = "O campo senha deve ser informado.")
    String senha,
    @NotBlank(message = "O campo CPF deve ser informado.")
    String cpf, 
    @NotNull (message = "Informe o(s) telefone(s).")
    List<TelefoneDTO> telefones,
    @NotNull (message = "Informe o(s) endereço(s).")
    List<EnderecoDTO> enderecos
    ) {
}