package dev.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Integridade {

    NOVO(1, "Novo"),
    USADO(2, "Usado"),
    REPARADO(3, "Reparado");

    private int id;
    private String label;

    Integridade(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static Integridade valueOf(Integer id) throws IllegalArgumentException {
        if (id == null)
            return null;

        for (Integridade integridade : Integridade.values()) {
            if (id.equals(integridade.getId())) 
                return integridade;
        }

        throw new IllegalArgumentException("Id inválido:" + id);
    }
}