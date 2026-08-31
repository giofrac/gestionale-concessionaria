package com.esempio.gestionale_concessionaria;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_veicolo")
public abstract class Veicolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modello;
    private int annoImmatricolazione;

    protected Veicolo() {}

    protected Veicolo(String marca, String modello, int annoImmatricolazione) {
        this.marca = marca;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public Long getId() { return id; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getAnnoImmatricolazione() { return annoImmatricolazione; }

    public abstract double calcolaTassaAnnuale();
}
