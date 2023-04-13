package br.ufpe.cin.residencia.recyclerview;

public class Pessoa {
    private String nome;
    private String login;
    private String email;
    private String site;
    private boolean legal;

    //...
    public Pessoa(String nome, String login) {
        this.nome = nome;
        this.login = login;
        this.email = login + "@cin.ufpe.br";
        this.site = "https://www.cin.ufpe.br/~" + login;
        this.legal = nome.length() % 2 == 0;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getSite() {
        return site;
    }

    public boolean isLegal() { return legal; }

}