package dev.application.dto;

import dev.application.model.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
    Long id,

    @NotBlank(message = "Npme é obrigatório") 
    String nome,

    @NotBlank(message = "Sobrenome é obrigatório") 
    String sobrenome,

    @NotBlank(message = "CEP é obrigatório") 
    String cep,

    @NotBlank(message = "Endereço é obrigatório") 
    String endereco,

    @NotBlank(message = "Número é obrigatório") 
    String numero,

    @NotBlank(message = "Bairro é obrigatório") 
    String bairro,

    @NotBlank(message = "Complemento é obrigatório") 
    String complemento,

    CidadeResponseDTO cidade,

    @NotBlank(message = "Telefone é obrigatório") 
    String telefone) {

  public static EnderecoDTO valueOf(Endereco endereco) {
    return new EnderecoDTO(
        endereco.getId(),
        endereco.getNome(),
        endereco.getSobrenome(),
        endereco.getCep(),
        endereco.getEndereco(),
        endereco.getNumero(),
        endereco.getBairro(),
        endereco.getComplemento(),
        CidadeResponseDTO.valueOf(endereco.getCidade()),
        endereco.getTelefone());
  }
}