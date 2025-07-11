package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    protected TipoUsuario tipo;

    public Usuario() {}

    public Usuario(String nome, String login, String senhaHash, TipoUsuario tipo) {
        this.nome = nome;
        this.login = login;
        this.senhaHash = senhaHash;
        this.tipo = tipo;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }

    public void atualizarInformacoesBasicas(String nome) {
        this.nome = nome;
    }

    public boolean validarSenha(String senha) {
        return this.senhaHash.equals(senha);
    }
}
