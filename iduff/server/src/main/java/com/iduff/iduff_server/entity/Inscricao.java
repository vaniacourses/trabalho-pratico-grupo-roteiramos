package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.StatusInscricao;
import java.time.LocalDate;
import java.util.UUID;

public class Inscricao {
    private String id;
    private LocalDate data;
    private String periodo;
    private StatusInscricao status;
    private Aluno aluno;
    private Turma turma;
    private Solicitacao solicitacao;

    // Constructors
    public Inscricao() {
        this.id = UUID.randomUUID().toString();
        this.data = LocalDate.now();
        this.status = StatusInscricao.ATIVA;
    }

    public Inscricao(String periodo, Aluno aluno, Turma turma, Solicitacao solicitacao) {
        this();
        this.periodo = periodo;
        this.aluno = aluno;
        this.turma = turma;
        this.solicitacao = solicitacao;
    }

    // Business methods
    public void concluir() {
        this.status = StatusInscricao.CONCLUIDA;
    }

    public void cancelar() {
        this.status = StatusInscricao.CANCELADA;
    }

    public void trancar() {
        this.status = StatusInscricao.TRANCADA;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public StatusInscricao getStatus() {
        return status;
    }

    public void setStatus(StatusInscricao status) {
        this.status = status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }
}
