package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;
import java.util.UUID;

public abstract class Usuario {
    protected String id;
    protected String nome;
    protected String login;
    protected String senhaHash;
    protected TipoUsuario tipo;

    // Constructors
    public Usuario() {
        this.id = UUID.randomUUID().toString();
    }

    public Usuario(String nome, String login, String senhaHash, TipoUsuario tipo) {
        this();
        this.nome = nome;
        this.login = login;
        this.senhaHash = senhaHash;
        this.tipo = tipo;
    }

    // Business methods
    public boolean validarSenha(String senha) {
        // In a real implementation, this would use proper password hashing
        return this.senhaHash.equals(senha);
    }

    public void atualizarInformacoesBasicas(String nome) {
        this.nome = nome;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
