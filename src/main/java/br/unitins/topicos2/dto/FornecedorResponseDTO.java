package br.unitins.topicos2.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.model.Fornecedor;
import br.unitins.topicos2.model.Hardware;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        List<EnderecoDTO> enderecos,
        List<HardwareDTO> hardwares) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        List<Hardware> hardwares = fornecedor.getHardwares();

        List<HardwareDTO> hardwaresDTO = null;
        if (hardwares != null && !hardwares.isEmpty()) {
            hardwaresDTO = hardwares.stream().map(hardware -> new HardwareDTO(
                    hardware.getMarca().getId(),
                    hardware.getNome(),
                    hardware.getPreco(),
                    hardware.getEstoque(),
                    hardware.getModelo(),
                    hardware.getFabricante().getId(),
                    hardware.getLancamento(),
                    null,
                    null)).collect(Collectors.toList());
        }
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEnderecos().stream().map(enderecos -> EnderecoDTO.valueOf(enderecos)).toList(),
                hardwaresDTO);
    }
}
