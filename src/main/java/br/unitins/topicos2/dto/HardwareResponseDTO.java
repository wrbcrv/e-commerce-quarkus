package br.unitins.topicos2.dto;

import java.time.format.DateTimeFormatter;

import br.unitins.topicos2.model.Hardware;

public record HardwareResponseDTO(
        Long id,
        String nome,
        Float preco,
        int estoque,
        String modelo,
        String lancamento,
        MarcaResponseDTO marca) { 

    public static HardwareResponseDTO valueOf(Hardware hardware) {
        String formattedLancamento = hardware.getLancamento() != null
                ? hardware.getLancamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        return new HardwareResponseDTO(
                hardware.getId(),
                hardware.getNome(),
                hardware.getPreco(),
                hardware.getEstoque(),
                hardware.getModelo(),
                formattedLancamento,
                MarcaResponseDTO.valueOf(hardware.getMarca())
        );
    }
}