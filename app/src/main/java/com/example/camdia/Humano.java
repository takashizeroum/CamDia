package com.example.camdia;

public class Humano {

    private String nome;
    private int id;
    private String desc;
    private int rank;
    private Double km;
    private int comp;


    public Humano(String nome, int id, String desc, int rank, Double km, int comp) {
        this.nome = nome;
        this.id = id;
        this.desc = desc;
        this.rank = rank;
        this.km = km;
        this.comp = comp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public int getComp() {
        return comp;
    }

    public void setComp(int comp) {
        this.comp = comp;
    }
}