package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.ICursoService;
import com.iduff.iduff_server.dto.DadosCurso;
import com.iduff.iduff_server.entity.Curso;
import com.iduff.iduff_server.entity.Disciplina;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@Service
public class CursoServiceImpl implements ICursoService {

    // In-memory storage for demo purposes
    private final Map<UUID, Curso> cursos = new HashMap<>();
    private final Map<UUID, Disciplina> disciplinas = new HashMap<>();
    private final Map<UUID, List<UUID>> cursoDisciplinas = new HashMap<>();

    @Override
    public Curso criarCurso(DadosCurso dados) {
        Curso curso = new Curso(dados.getNome(), dados.getCodigo());
        cursos.put(curso.getId(), curso);
        cursoDisciplinas.put(curso.getId(), new ArrayList<>());
        return curso;
    }

    @Override
    public Curso atualizarCurso(UUID cursoId, DadosCurso dados) {
        Curso curso = cursos.get(cursoId);

        if (curso != null) {
            curso.setNome(dados.getNome());
            curso.setCodigo(dados.getCodigo());
        }

        return curso;
    }

    @Override
    public Curso obterCurso(UUID cursoId) {
        return cursos.get(cursoId);
    }

    @Override
    public List<Curso> listarCursos() {
        return new ArrayList<>(cursos.values());
    }

    @Override
    public void associarDisciplinaAoCurso(UUID cursoId, UUID disciplinaId) {
        if (cursos.containsKey(cursoId) && disciplinas.containsKey(disciplinaId)) {
            List<UUID> disciplinasDoCurso = cursoDisciplinas.get(cursoId);
            if (!disciplinasDoCurso.contains(disciplinaId)) {
                disciplinasDoCurso.add(disciplinaId);
            }
        }
    }

    @Override
    public void desassociarDisciplinaDoCurso(UUID cursoId, UUID disciplinaId) {
        if (cursos.containsKey(cursoId)) {
            List<UUID> disciplinasDoCurso = cursoDisciplinas.get(cursoId);
            disciplinasDoCurso.remove(disciplinaId);
        }
    }
}
