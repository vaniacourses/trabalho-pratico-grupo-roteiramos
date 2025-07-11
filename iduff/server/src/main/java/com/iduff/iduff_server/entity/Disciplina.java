package com.iduff.iduff_server.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int cargaHoraria;

    @Column
    private String ementa;

    @ManyToMany
    @JoinTable(
        name = "disciplina_prerequisito",
        joinColumns = @JoinColumn(name = "disciplina_id"),
        inverseJoinColumns = @JoinColumn(name = "prerequisito_id")
    )
    private List<Disciplina> preRequisitos;

    public Disciplina() {}

    public Disciplina(String codigo, String nome, int cargaHoraria, String ementa) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.ementa = ementa;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getEmenta() { return ementa; }
    public void setEmenta(String ementa) { this.ementa = ementa; }
    public List<Disciplina> getPreRequisitos() { return preRequisitos; }
    public void setPreRequisitos(List<Disciplina> preRequisitos) { this.preRequisitos = preRequisitos; }

    public void adicionarPreRequisito(Disciplina disciplina) {
        if (preRequisitos == null) preRequisitos = new ArrayList<>();
        if (!preRequisitos.contains(disciplina)) preRequisitos.add(disciplina);
    }

    public void removerPreRequisito(Disciplina disciplina) {
        if (preRequisitos != null) preRequisitos.remove(disciplina);
    }
}
