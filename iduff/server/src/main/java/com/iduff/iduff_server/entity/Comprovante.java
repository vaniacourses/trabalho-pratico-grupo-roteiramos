package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoComprovante;
import java.time.LocalDate;
import java.util.UUID;

public class Comprovante {
    private String id;
    private LocalDate dataEmissao;
    private TipoComprovante tipo;
    private String conteudo;
    private Inscricao inscricao;
    private Aluno aluno;

    // Constructors
    public Comprovante() {
        this.id = UUID.randomUUID().toString();
        this.dataEmissao = LocalDate.now();
    }

    public Comprovante(TipoComprovante tipo, String conteudo) {
        this();
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public TipoComprovante getTipo() {
        return tipo;
    }

    public void setTipo(TipoComprovante tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
