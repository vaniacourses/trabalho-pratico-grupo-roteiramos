package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;

public class Professor extends Usuario {
    private String departamento;

    // Constructors
    public Professor() {
        super();
        this.tipo = TipoUsuario.PROFESSOR;
    }

    public Professor(String nome, String login, String senhaHash, String departamento) {
        super(nome, login, senhaHash, TipoUsuario.PROFESSOR);
        this.departamento = departamento;
    }

    // Getters and Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
