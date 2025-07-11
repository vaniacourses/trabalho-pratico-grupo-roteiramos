package com.iduff.iduff_server.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "disciplina_cursada")
public class DisciplinaCursada {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDate dataConclusao;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "nota_id")
    private Nota nota;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public DisciplinaCursada() {}

    public DisciplinaCursada(Disciplina disciplina, Turma turma, Nota nota, Aluno aluno) {
        this.disciplina = disciplina;
        this.turma = turma;
        this.nota = nota;
        this.aluno = aluno;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDate getDataConclusao() { return dataConclusao; }
    public void setDataConclusao(LocalDate dataConclusao) { this.dataConclusao = dataConclusao; }
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }
    public Nota getNota() { return nota; }
    public void setNota(Nota nota) { this.nota = nota; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}
