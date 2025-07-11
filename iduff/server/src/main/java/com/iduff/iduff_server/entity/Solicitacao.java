package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.StatusSolicitacao;
import com.iduff.iduff_server.enums.TipoSolicitacao;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusSolicitacao status;

    @Enumerated(EnumType.STRING)
    @Column
    private TipoSolicitacao tipo;

    @Column
    private String motivoRejeicao;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Solicitacao() {}

    public Solicitacao(TipoSolicitacao tipo, Aluno aluno, Turma turma) {
        this.tipo = tipo;
        this.aluno = aluno;
        this.turma = turma;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public StatusSolicitacao getStatus() { return status; }
    public void setStatus(StatusSolicitacao status) { this.status = status; }
    public TipoSolicitacao getTipo() { return tipo; }
    public void setTipo(TipoSolicitacao tipo) { this.tipo = tipo; }
    public String getMotivoRejeicao() { return motivoRejeicao; }
    public void setMotivoRejeicao(String motivoRejeicao) { this.motivoRejeicao = motivoRejeicao; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public void aprovar(Coordenador coordenador) {
        this.status = StatusSolicitacao.APROVADA;
        // Adicione lógica extra se necessário
    }

    public void reprovar(Coordenador coordenador, String motivo) {
        this.status = StatusSolicitacao.REPROVADA;
        this.motivoRejeicao = motivo;
        // Adicione lógica extra se necessário
    }
}
