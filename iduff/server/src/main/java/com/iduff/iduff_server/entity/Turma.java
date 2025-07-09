package com.iduff.iduff_server.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Turma {
    private String id;
    private String nome;
    private int limiteVagas;
    private String local;
    private Disciplina disciplina;
    private Professor professor;
    private List<Horario> horarios;

    // Constructors
    public Turma() {
        this.id = UUID.randomUUID().toString();
        this.horarios = new ArrayList<>();
    }

    public Turma(String nome, int limiteVagas, String local, Disciplina disciplina, Professor professor) {
        this();
        this.nome = nome;
        this.limiteVagas = limiteVagas;
        this.local = local;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    // Business methods
    public boolean verificarDisponibilidade() {
        // This would check current enrollments against limit
        // For now, just return true
        return true;
    }

    public void adicionarHorario(Horario horario) {
        this.horarios.add(horario);
    }

    public void removerHorario(Horario horario) {
        this.horarios.remove(horario);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLimiteVagas() {
        return limiteVagas;
    }

    public void setLimiteVagas(int limiteVagas) {
        this.limiteVagas = limiteVagas;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }
}
