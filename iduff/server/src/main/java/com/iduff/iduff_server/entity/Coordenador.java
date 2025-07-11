package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("COORDENADOR")
public class Coordenador extends Usuario {

    @ManyToOne
    @JoinColumn(name = "curso_coordenado_id")
    private Curso cursoCoordenado;

    public Coordenador() {
        super();
        this.tipo = TipoUsuario.COORDENADOR;
    }

    public Coordenador(String nome, String login, String senhaHash, Curso cursoCoordenado) {
        super(nome, login, senhaHash, TipoUsuario.COORDENADOR);
        this.cursoCoordenado = cursoCoordenado;
    }

    public Curso getCursoCoordenado() {
        return cursoCoordenado;
    }

    public void setCursoCoordenado(Curso cursoCoordenado) {
        this.cursoCoordenado = cursoCoordenado;
    }
}
