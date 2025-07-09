package com.iduff.iduff_server.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Disciplina {
    private String id;
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private String ementa;
    private List<Disciplina> preRequisitos;

    // Constructors
    public Disciplina() {
        this.id = UUID.randomUUID().toString();
        this.preRequisitos = new ArrayList<>();
    }

    public Disciplina(String codigo, String nome, int cargaHoraria, String ementa) {
        this();
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
    }

    // Business methods
    public void adicionarPreRequisito(Disciplina preRequisito) {
        if (!this.preRequisitos.contains(preRequisito)) {
            this.preRequisitos.add(preRequisito);
        }
    }

    public void removerPreRequisito(Disciplina preRequisito) {
        this.preRequisitos.remove(preRequisito);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }
}
