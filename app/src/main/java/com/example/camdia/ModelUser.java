package com.example.camdia;

public class ModelUser {

    private String nome;
    private String login;
    private String empresa;
    private String senha;
    private int id;
    private String desc;
    private int rank;
    private Double km;
    private int compete;
    private Double tempo;

    public ModelUser(String nome, String login, String empresa, String senha, int id, String desc, int rank, Double km, int compete, Double tempo) {
        this.nome = nome;
        this.login = login;
        this.empresa = empresa;
        this.senha = senha;
        this.id = id;
        this.desc = desc;
        this.rank = rank;
        this.km = km;
        this.compete = compete;
        this.tempo = tempo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public int getCompete() {
        return compete;
    }

    public void setCompete(int compete) {
        this.compete = compete;
    }

    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }
}