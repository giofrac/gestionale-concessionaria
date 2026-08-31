package com.esempio.gestionale_concessionaria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Autore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Libro> libri = new ArrayList<>();

    protected Autore() {}

    public Autore(String nome) {
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public List<Libro> getLibri() { return libri; }

    public void aggiungiLibro(Libro libro) {
        libri.add(libro);
        libro.setAutore(this);
    }
}
