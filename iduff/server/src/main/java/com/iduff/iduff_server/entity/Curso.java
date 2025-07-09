package com.iduff.iduff_server.entity;

import java.util.UUID;

public class Curso {
    private String id;
    private String nome;
    private String codigo;

    // Constructors
    public Curso() {
        this.id = UUID.randomUUID().toString();
    }

    public Curso(String nome, String codigo) {
        this();
        this.nome = nome;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
