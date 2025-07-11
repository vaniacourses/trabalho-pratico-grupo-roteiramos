package com.iduff.iduff_server.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "nota")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private double valor;

    @Column
    private LocalDate dataLancamento;

    @Column
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "inscricao_id")
    private Inscricao inscricao;

    public Nota() {}

    public Nota(double valor, String observacoes, Inscricao inscricao) {
        this.valor = valor;
        this.observacoes = observacoes;
        this.inscricao = inscricao;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public Inscricao getInscricao() { return inscricao; }
    public void setInscricao(Inscricao inscricao) { this.inscricao = inscricao; }

    public boolean validarValor() {
        return valor >= 0.0 && valor <= 10.0;
    }
}
