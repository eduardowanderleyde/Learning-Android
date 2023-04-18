package br.ufpe.cin.residencia.datamanagement.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "professores")
public class Professor {

    @PrimaryKey @NonNull
    public final String login;
    public final String nome;
    public final String email;
    public final String site;
    public final boolean legal;

    public Professor(String nome, String login, String email, String site, boolean legal) {
        this.nome = nome;
        this.login = login;
        this.legal = legal;
        this.email = email;
        this.site = site;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "login='" + login + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", legal=" + legal +
                '}';
    }
}
