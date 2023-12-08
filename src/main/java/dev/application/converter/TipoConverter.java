package dev.application.converter;

import dev.application.model.Tipo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoConverter implements AttributeConverter<Tipo, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Tipo tipo) {
        return tipo == null ? null : tipo.getId();
    }

    @Override
    public Tipo convertToEntityAttribute(Integer id) {
        return Tipo.valueOf(id);
    }
}