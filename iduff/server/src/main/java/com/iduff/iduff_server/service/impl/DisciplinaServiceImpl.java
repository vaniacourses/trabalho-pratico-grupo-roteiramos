package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.IDisciplinaService;
import com.iduff.iduff_server.dto.DadosDisciplina;
import com.iduff.iduff_server.entity.Disciplina;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

@Service
public class DisciplinaServiceImpl implements IDisciplinaService {

    // In-memory storage for demo purposes
    private final Map<UUID, Disciplina> disciplinas = new HashMap<>();

    @Override
    public Disciplina criarDisciplina(DadosDisciplina dados) {
        Disciplina disciplina = new Disciplina(dados.getCodigo(), dados.getNome(), dados.getCargaHoraria(),
                dados.getEmenta());
        disciplinas.put(disciplina.getId(), disciplina);
        return disciplina;
    }

    @Override
    public Disciplina atualizarDisciplina(UUID disciplinaId, DadosDisciplina dados) {
        Disciplina disciplina = disciplinas.get(disciplinaId);

        if (disciplina != null) {
            disciplina.setCodigo(dados.getCodigo());
            disciplina.setNome(dados.getNome());
            disciplina.setCargaHoraria(dados.getCargaHoraria());
            disciplina.setEmenta(dados.getEmenta());
        }

        return disciplina;
    }

    @Override
    public Disciplina obterDisciplina(UUID disciplinaId) {
        return disciplinas.get(disciplinaId);
    }

    @Override
    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas.values());
    }

    @Override
    public void associarPreRequisito(UUID disciplinaId, UUID preRequisitoId) {
        Disciplina disciplina = disciplinas.get(disciplinaId);
        Disciplina preRequisito = disciplinas.get(preRequisitoId);

        if (disciplina != null && preRequisito != null) {
            disciplina.adicionarPreRequisito(preRequisito);
        }
    }

    @Override
    public void desassociarPreRequisito(UUID disciplinaId, UUID preRequisitoId) {
        Disciplina disciplina = disciplinas.get(disciplinaId);
        Disciplina preRequisito = disciplinas.get(preRequisitoId);

        if (disciplina != null && preRequisito != null) {
            disciplina.removerPreRequisito(preRequisito);
        }
    }
}
