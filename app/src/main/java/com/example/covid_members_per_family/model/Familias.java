package com.example.covid_members_per_family.model;

import java.io.Serializable;

public class Familias implements Serializable {

    private Long id;
    private String sobrenome;
    private String numFamiliares;
    private String numInfectados;

    @Override
    public String toString() {
        return sobrenome.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNumFamiliares() {
        return numFamiliares;
    }

    public void setNumFamiliares(String numFamiliares) {
        this.numFamiliares = numFamiliares;
    }

    public String getNumInfectados() {
        return numInfectados;
    }

    public void setNumInfectados(String numInfectados) {
        this.numInfectados = numInfectados;
    }
}
