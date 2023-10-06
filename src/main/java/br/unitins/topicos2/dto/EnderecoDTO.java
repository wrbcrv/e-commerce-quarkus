package br.unitins.topicos2.dto;

import br.unitins.topicos2.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        Long id,
        @NotBlank(message = "O campo logradouro deve ser informado.")
        String logradouro,
        @NotBlank(message = "O campo número deve ser informado.")
        String numero,
        @NotBlank(message = "O campo complemento deve ser informado.")
        String complemento,
        @NotBlank(message = "O campo bairro deve ser informado.")
        String bairro,
        @NotBlank(message = "O campo CEP deve ser informado.")
        String cep) {

    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
            endereco.getId(),
            endereco.getLogradouro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getBairro(),
            endereco.getCep()
        );
    }
}