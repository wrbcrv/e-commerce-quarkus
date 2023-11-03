package dev.application.dto;

import dev.application.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        Long id,
        @NotBlank(message = "O campo Logradouro deve ser informado.")
        String logradouro,

        @NotBlank(message = "O campo Número deve ser informado.")
        String numero,

        @NotBlank(message = "O campo Complemento deve ser informado.")
        String complemento,

        @NotBlank(message = "O campo Bairro deve ser informado.")
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