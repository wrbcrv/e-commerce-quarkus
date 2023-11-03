package dev.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
    @NotBlank(message = "O campo Nome deve ser informado.")
    @Size(max = 60, message = "O campo nome deve possuir no máximo 60 caracteres.")
    String nome,
    
    @NotBlank(message = "O campo Sigla deve ser informado.")
    String login,

    @NotBlank(message = "O campo Senha deve ser informado.")
    String senha,

    @NotBlank(message = "O campo CPF deve ser informado.")
    String cpf, 

    @NotNull(message = "Informe o(s) telefone(s).")
    List<TelefoneDTO> telefones,

    @NotNull(message = "Informe o(s) endereço(s).")
    List<EnderecoDTO> enderecos) {
}