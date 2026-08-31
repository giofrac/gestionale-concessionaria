package com.esempio.gestionale_concessionaria;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTOCICLETTA")
public class Motocicletta extends Veicolo {
    private int cilindrata;

    public Motocicletta () {}

    public Motocicletta(String marca, String modello, int annoImmatricolazione, int cilindrata) {
        super(marca, modello, annoImmatricolazione);
        this.cilindrata = cilindrata;
    }

    @Override
    public double calcolaTassaAnnuale(){
        return 20 + cilindrata * 0.05;
    }
}
