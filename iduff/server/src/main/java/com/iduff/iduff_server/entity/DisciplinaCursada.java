package com.iduff.iduff_server.entity;

import java.time.LocalDate;
import java.util.UUID;

public class DisciplinaCursada {
    private String id;
    private LocalDate dataConclusao;
    private Disciplina disciplina;
    private Turma turma;
    private Nota nota;
    private Aluno aluno;

    // Constructors
    public DisciplinaCursada() {
        this.id = UUID.randomUUID().toString();
    }

    public DisciplinaCursada(Disciplina disciplina, Turma turma, Nota nota, Aluno aluno) {
        this();
        this.disciplina = disciplina;
        this.turma = turma;
        this.nota = nota;
        this.aluno = aluno;
        this.dataConclusao = LocalDate.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
