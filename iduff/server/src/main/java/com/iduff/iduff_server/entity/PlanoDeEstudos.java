package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.StatusPlano;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plano_de_estudos")
public class PlanoDeEstudos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusPlano status;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToMany
    @JoinTable(
        name = "plano_disciplina",
        joinColumns = @JoinColumn(name = "plano_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;

    public PlanoDeEstudos() {}

    public PlanoDeEstudos(Aluno aluno) {
        this.aluno = aluno;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public StatusPlano getStatus() { return status; }
    public void setStatus(StatusPlano status) { this.status = status; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public List<Disciplina> getDisciplinas() { return disciplinas; }
    public void setDisciplinas(List<Disciplina> disciplinas) { this.disciplinas = disciplinas; }
}
