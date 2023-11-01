package dev.application.converter;

import dev.application.model.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Status integridade) {
        return integridade == null ? null : integridade.getId();
    }

    @Override
    public Status convertToEntityAttribute(Integer id) {
        return Status.valueOf(id);
    }
}