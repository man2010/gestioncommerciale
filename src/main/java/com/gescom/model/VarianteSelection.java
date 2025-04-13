package com.gescom.model;

import java.io.Serializable;
import java.util.Objects;

public class VarianteSelection implements Serializable {
    private String type;
    private String valeur;

    public VarianteSelection() {}

    public VarianteSelection(String type, String valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    // Getters et Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getValeur() { return valeur; }
    public void setValeur(String valeur) { this.valeur = valeur; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VarianteSelection)) return false;
        VarianteSelection that = (VarianteSelection) o;
        return type.equals(that.type) && valeur.equals(that.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, valeur);
    }
}