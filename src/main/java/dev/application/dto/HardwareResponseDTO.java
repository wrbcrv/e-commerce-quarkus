package dev.application.dto;

import java.time.format.DateTimeFormatter;

import dev.application.model.Categoria;
import dev.application.model.Hardware;
import dev.application.model.Status;

public record HardwareResponseDTO(
        Long id,
        MarcaResponseDTO marca,
        String nome,
        Float preco,
        int estoque,
        String modelo,
        FabricanteResponseDTO fabricante,
        String lancamento,
        Categoria categoria,
        Status status) { 

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
                FabricanteResponseDTO.valueOf(hardware.getFabricante()),
                formattedLancamento,
                hardware.getCategoria(),
                hardware.getStatus()
        );
    }
}