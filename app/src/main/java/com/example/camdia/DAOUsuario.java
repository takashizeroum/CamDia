package com.example.camdia;

public class DAOUsuario {
    int id;
    String login ;
    String senha ;
    String empresa ;



    public DAOUsuario(int id, String empresa, String login, String senha) {
        this.id = id;
        this.empresa = empresa;
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
