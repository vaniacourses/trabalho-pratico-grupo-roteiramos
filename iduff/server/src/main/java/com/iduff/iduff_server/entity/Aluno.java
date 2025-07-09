package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;

public class Aluno extends Usuario {
    private String matricula;

    // Constructors
    public Aluno() {
        super();
        this.tipo = TipoUsuario.ALUNO;
    }

    public Aluno(String nome, String login, String senhaHash, String matricula) {
        super(nome, login, senhaHash, TipoUsuario.ALUNO);
        this.matricula = matricula;
    }

    // Getters and Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
