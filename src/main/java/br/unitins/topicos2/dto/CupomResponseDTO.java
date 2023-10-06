package br.unitins.topicos2.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos2.model.Cupom;
import br.unitins.topicos2.model.Hardware;

public record CupomResponseDTO(
        Long id,
        String descricao,
        String codigo,
        String inicio,
        String termino,
        int desconto,
        List<HardwareDTO> hardwares) {

    public static CupomResponseDTO valueOf(Cupom cupom) {
        String formattedInicio = cupom.getInicio() != null
                ? cupom.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;
        String formattedTermino = cupom.getTermino() != null
                ? cupom.getTermino().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : null;

        List<Hardware> hardwares = cupom.getHardwares();

        List<HardwareDTO> hardwaresDTO = null;
        if (hardwares != null && !hardwares.isEmpty()) {
            hardwaresDTO = hardwares.stream().map(hardware -> new HardwareDTO(
                hardware.getMarca().getId(),
                hardware.getNome(),
                hardware.getPreco(),
                hardware.getEstoque(),
                hardware.getModelo(),
                hardware.getLancamento(),
                hardware.getIntegridade().getId(),
                hardware.getCategoria().getId()
            )).collect(Collectors.toList());
        }

        return new CupomResponseDTO(
                cupom.getId(),
                cupom.getDescricao(),
                cupom.getCodigo(),
                formattedInicio,
                formattedTermino,
                cupom.getDesconto(),
                hardwaresDTO);
    }
}