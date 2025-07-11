package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESSOR")
public class Professor extends Usuario {

    @Column(nullable = true)
    private String departamento;

    public Professor() {
        super();
        this.tipo = TipoUsuario.PROFESSOR;
    }

    public Professor(String nome, String login, String senhaHash, String departamento) {
        super(nome, login, senhaHash, TipoUsuario.PROFESSOR);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
