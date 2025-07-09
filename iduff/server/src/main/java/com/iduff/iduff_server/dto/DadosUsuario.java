package com.iduff.iduff_server.dto;

import com.iduff.iduff_server.enums.TipoUsuario;

public class DadosUsuario {
    private String nome;
    private String login;
    private String senha;
    private TipoUsuario tipo;
    private String matricula; // Para alunos
    private String departamento; // Para professores
    private String cursoId; // Para coordenadores

    // Constructors
    public DadosUsuario() {
    }

    public DadosUsuario(String nome, String login, String senha, TipoUsuario tipo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCursoId() {
        return cursoId;
    }

    public void setCursoId(String cursoId) {
        this.cursoId = cursoId;
    }
}
