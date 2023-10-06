package br.unitins.topicos2.dto;

import java.time.format.DateTimeFormatter;

import br.unitins.topicos2.model.Categoria;
import br.unitins.topicos2.model.Hardware;
import br.unitins.topicos2.model.Integridade;

public record HardwareResponseDTO(
        Long id,
        MarcaResponseDTO marca,
        String nome,
        Float preco,
        int estoque,
        String modelo,
        String lancamento,
        DescricaoResponseDTO descricao,
        Integridade integridade,
        Categoria categoria) { 

    public static HardwareResponseDTO valueOf(Hardware hardware) {
        String formattedLancamento = hardware.getLancamento() != null
                ? hardware.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        return new HardwareResponseDTO(
                hardware.getId(),
                MarcaResponseDTO.valueOf(hardware.getMarca()),
                hardware.getNome(),
                hardware.getPreco(),
                hardware.getEstoque(),
                hardware.getModelo(),
                formattedLancamento,
                DescricaoResponseDTO.valueOf(hardware.getDescricao()),
                hardware.getIntegridade(),
                hardware.getCategoria()
        );
    }
}