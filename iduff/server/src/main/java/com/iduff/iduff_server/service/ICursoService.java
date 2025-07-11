package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosCurso;
import com.iduff.iduff_server.entity.Curso;
import java.util.List;
import java.util.UUID;

public interface ICursoService {
    Curso criarCurso(DadosCurso dados);

    Curso atualizarCurso(UUID cursoId, DadosCurso dados);

    Curso obterCurso(UUID cursoId);

    List<Curso> listarCursos();

    void associarDisciplinaAoCurso(UUID cursoId, UUID disciplinaId);

    void desassociarDisciplinaDoCurso(UUID cursoId, UUID disciplinaId);
}
