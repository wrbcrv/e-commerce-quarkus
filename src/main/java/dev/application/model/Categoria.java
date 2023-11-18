package dev.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Categoria {

    ENTRADA(1, "Entrada"),
    MID_END(2, "Mid-End"),
    HIGH_END(3, "High-End");

    private int id;
    private String label;

    Categoria(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Categoria valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for (Categoria categoria : Categoria.values()) {
            if (id.equals(categoria.getId()))
                return categoria;
        }

        throw new IllegalArgumentException("Id inválido:" + id);
    }
}