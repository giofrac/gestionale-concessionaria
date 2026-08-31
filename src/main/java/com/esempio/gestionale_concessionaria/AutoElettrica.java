package com.esempio.gestionale_concessionaria;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AUTOELETTRICA")
public class AutoElettrica extends Veicolo implements Elettrico {
    private double autonomia;

    public AutoElettrica() {}

    public AutoElettrica(String marca, String modello, int annoImmatricolazione, double autonomia) {
        super(marca, modello, annoImmatricolazione);
        this.autonomia = autonomia;
    }

    public double autonomiaKm() {
        return autonomia;
    }

    @Override
    public double calcolaTassaAnnuale() {
        return 30;
    }
}
