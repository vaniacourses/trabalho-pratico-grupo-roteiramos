package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.TipoComprovante;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "comprovante")
public class Comprovante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDate dataEmissao;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoComprovante tipo;

    @Column
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "inscricao_id")
    private Inscricao inscricao;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Comprovante() {}

    public Comprovante(TipoComprovante tipo, String conteudo) {
        this.tipo = tipo;
        this.conteudo = conteudo;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }
    public TipoComprovante getTipo() { return tipo; }
    public void setTipo(TipoComprovante tipo) { this.tipo = tipo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public Inscricao getInscricao() { return inscricao; }
    public void setInscricao(Inscricao inscricao) { this.inscricao = inscricao; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}
