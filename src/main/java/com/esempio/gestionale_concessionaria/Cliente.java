package com.esempio.gestionale_concessionaria;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefono;

    protected Cliente() {
    }

    public Cliente(String nome, String email, String telefono) {
        this.nome = nome;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}
