package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.StatusSolicitacao;
import com.iduff.iduff_server.enums.TipoSolicitacao;
import java.time.LocalDate;
import java.util.UUID;

public class Solicitacao {
    private String id;
    private LocalDate data;
    private StatusSolicitacao status;
    private TipoSolicitacao tipo;
    private String motivoRejeicao;
    private Aluno aluno;
    private Turma turma;

    // Constructors
    public Solicitacao() {
        this.id = UUID.randomUUID().toString();
        this.data = LocalDate.now();
        this.status = StatusSolicitacao.PENDENTE;
    }

    public Solicitacao(TipoSolicitacao tipo, Aluno aluno, Turma turma) {
        this();
        this.tipo = tipo;
        this.aluno = aluno;
        this.turma = turma;
    }

    // Business methods
    public void aprovar(Coordenador coordenador) {
        this.status = StatusSolicitacao.APROVADA;
        this.motivoRejeicao = null;
    }

    public void reprovar(Coordenador coordenador, String motivo) {
        this.status = StatusSolicitacao.REPROVADA;
        this.motivoRejeicao = motivo;
    }

    public void cancelar() {
        this.status = StatusSolicitacao.CANCELADA;
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

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }

    public TipoSolicitacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSolicitacao tipo) {
        this.tipo = tipo;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
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
}
