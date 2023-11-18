package dev.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {

    NOVO(1, "Novo"),
    USADO(2, "Usado"),
    REPARADO(3, "Reparado");

    private int id;
    private String label;

    Status(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Status valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for (Status status : Status.values()) {
            if (id.equals(status.getId())) 
                return status;
        }

        throw new IllegalArgumentException("Id inválido:" + id);
    }
}