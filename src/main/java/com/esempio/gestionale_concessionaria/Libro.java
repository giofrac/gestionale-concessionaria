package com.esempio.gestionale_concessionaria;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titolo;
    private int anno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autore_id")
    private Autore autore;

    protected Libro() {}

    public Libro(String titolo, int anno) {
        this.titolo = titolo;
        this.anno = anno;
    }

    public Long getId() { return id; }
    public String getTitolo() { return titolo; }
    public int getAnno() { return anno; }
    public Autore getAutore() { return autore; }
    public void setAutore(Autore autore) { this.autore = autore; }

    @ManyToMany
    @JoinTable(
            name = "libro_genere",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "genere_id")
    )
    private List<Genere> generi = new ArrayList<>();

    public void aggiungiGenere(Genere genere) {
        generi.add(genere);
    }

    public List<Genere> getGeneri() { return generi; }
}
