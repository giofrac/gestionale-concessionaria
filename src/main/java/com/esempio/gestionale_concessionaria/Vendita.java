package com.esempio.gestionale_concessionaria;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Vendita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate dataVendita;
    private double prezzo;

    protected Vendita() {}

    public Vendita(Veicolo veicolo, Cliente cliente, LocalDate dataVendita, double prezzo) {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo");
        }
        this.veicolo = veicolo;
        this.cliente = cliente;
        this.dataVendita = dataVendita;
        this.prezzo = prezzo;
    }

    public Long getId() { return id; }
    public Veicolo getVeicolo() { return veicolo; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getDataVendita() { return dataVendita; }
    public double getPrezzo() { return prezzo; }
}