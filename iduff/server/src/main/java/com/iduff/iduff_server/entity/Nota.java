package com.iduff.iduff_server.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Nota {
    private String id;
    private double valor;
    private LocalDate dataLancamento;
    private String observacoes;
    private Inscricao inscricao;

    // Constructors
    public Nota() {
        this.id = UUID.randomUUID().toString();
        this.dataLancamento = LocalDate.now();
    }

    public Nota(double valor, String observacoes, Inscricao inscricao) {
        this();
        this.valor = valor;
        this.observacoes = observacoes;
        this.inscricao = inscricao;
    }

    // Business methods
    public boolean validarValor() {
        return valor >= 0.0 && valor <= 10.0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }
}
