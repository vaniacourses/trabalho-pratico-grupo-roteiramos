package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;

public class Coordenador extends Usuario {
    private Curso cursoCoordenado;

    // Constructors
    public Coordenador() {
        super();
        this.tipo = TipoUsuario.COORDENADOR;
    }

    public Coordenador(String nome, String login, String senhaHash, Curso cursoCoordenado) {
        super(nome, login, senhaHash, TipoUsuario.COORDENADOR);
        this.cursoCoordenado = cursoCoordenado;
    }

    // Getters and Setters
    public Curso getCursoCoordenado() {
        return cursoCoordenado;
    }

    public void setCursoCoordenado(Curso cursoCoordenado) {
        this.cursoCoordenado = cursoCoordenado;
    }
}
