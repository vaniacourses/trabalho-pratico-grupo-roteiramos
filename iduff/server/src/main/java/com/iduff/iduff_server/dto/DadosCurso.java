package com.iduff.iduff_server.dto;

public class DadosCurso {
    private String nome;
    private String codigo;

    // Constructors
    public DadosCurso() {
    }

    public DadosCurso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
