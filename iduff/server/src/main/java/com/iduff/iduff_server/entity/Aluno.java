package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ALUNO")
public class Aluno extends Usuario {

    @Column(nullable = true, unique = true)
    private String matricula;

    public Aluno() {
        super();
        this.tipo = TipoUsuario.ALUNO;
    }

    public Aluno(String nome, String login, String senhaHash, String matricula) {
        super(nome, login, senhaHash, TipoUsuario.ALUNO);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
