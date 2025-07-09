package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.StatusPlano;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class PlanoDeEstudos {
    private String id;
    private StatusPlano status;
    private Aluno aluno;
    private List<Disciplina> disciplinas;

    // Constructors
    public PlanoDeEstudos() {
        this.id = UUID.randomUUID().toString();
        this.status = StatusPlano.ATIVO;
        this.disciplinas = new ArrayList<>();
    }

    public PlanoDeEstudos(Aluno aluno) {
        this();
        this.aluno = aluno;
    }

    // Business methods
    public void adicionarDisciplina(Disciplina disciplina) {
        if (!this.disciplinas.contains(disciplina)) {
            this.disciplinas.add(disciplina);
        }
    }

    public void removerDisciplina(Disciplina disciplina) {
        this.disciplinas.remove(disciplina);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusPlano getStatus() {
        return status;
    }

    public void setStatus(StatusPlano status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
