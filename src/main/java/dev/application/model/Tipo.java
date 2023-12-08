package dev.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Tipo {

  CREDITO(1, "Cartão de Crédito"),
  DEBITO(2, "Cartão de Débito");

  private int id;
  private String label;

  Tipo(int id, String label) {
    this.id = id;
    this.label = label;
  }

  public int getId() {
    return id;
  }

  public String getLabel() {
    return label;
  }

  public static Tipo valueOf(Integer id) throws IllegalArgumentException {
    if (id == null)
      return null;

    for (Tipo tipo : Tipo.values()) {
      if (id.equals(tipo.getId()))
        return tipo;
    }

    throw new IllegalArgumentException("ID inválido: " + id);
  }
}
