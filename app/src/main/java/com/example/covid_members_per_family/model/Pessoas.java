package com.example.covid_members_per_family.model;

import java.io.Serializable;

public class Pessoas implements Serializable {

    private Long id;
    private String email;
    private String nome;
    private String bairro;

    @Override
    public String toString() {
        return nome.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
