package com.iduff.iduff_server.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int limiteVagas;

    @Column
    private String local;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "turma")
    private List<Horario> horarios;

    public Turma() {}

    public Turma(String nome, int limiteVagas, String local, Disciplina disciplina, Professor professor) {
        this.nome = nome;
        this.limiteVagas = limiteVagas;
        this.local = local;
        this.disciplina = disciplina;
        this.professor = professor;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getLimiteVagas() { return limiteVagas; }
    public void setLimiteVagas(int limiteVagas) { this.limiteVagas = limiteVagas; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public List<Horario> getHorarios() { return horarios; }
    public void setHorarios(List<Horario> horarios) { this.horarios = horarios; }
}
