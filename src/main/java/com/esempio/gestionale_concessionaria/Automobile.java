package com.esempio.gestionale_concessionaria;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AUTOMOBILE")
public class Automobile extends Veicolo {

    private int numeroPosti;

    protected Automobile() {}

    public Automobile(String marca, String modello, int anno, int numeroPosti) {
        super(marca, modello, anno);
        this.numeroPosti = numeroPosti;
    }

    @Override
    public double calcolaTassaAnnuale() {
        return 50 + numeroPosti * 10;
    }
}

