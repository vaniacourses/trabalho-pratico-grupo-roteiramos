package com.iduff.iduff_server.dto;

public class DadosDisciplina {
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private String ementa;

    // Constructors
    public DadosDisciplina() {
    }

    public DadosDisciplina(String codigo, String nome, int cargaHoraria, String ementa) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
    }

    // Getters and Setters
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
}
