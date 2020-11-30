package com.example.covid_members_per_family.model;

public class Familias {

    private Long id;
    private String sobrenome;
    private int numFamiliares;
    private int numInfectados;

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

    public int getNumFamiliares() {
        return numFamiliares;
    }

    public void setNumFamiliares(int numFamiliares) {
        this.numFamiliares = numFamiliares;
    }

    public int getNumInfectados() {
        return numInfectados;
    }

    public void setNumInfectados(int numInfectados) {
        this.numInfectados = numInfectados;
    }
}
