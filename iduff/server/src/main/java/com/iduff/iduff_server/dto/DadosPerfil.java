package com.iduff.iduff_server.dto;

public class DadosPerfil {
    private String nome;
    private String departamento; // Para professores
    private String matricula; // Para alunos

    // Constructors
    public DadosPerfil() {
    }

    public DadosPerfil(String nome) {
        this.nome = nome;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
