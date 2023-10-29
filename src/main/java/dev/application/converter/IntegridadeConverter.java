package dev.application.converter;

import dev.application.model.Integridade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IntegridadeConverter implements AttributeConverter<Integridade, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Integridade integridade) {
        return integridade == null ? null : integridade.getId();
    }

    @Override
    public Integridade convertToEntityAttribute(Integer id) {
        return Integridade.valueOf(id);
    }
}