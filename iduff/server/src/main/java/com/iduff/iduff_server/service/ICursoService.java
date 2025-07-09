package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosCurso;
import com.iduff.iduff_server.entity.Curso;
import java.util.List;

public interface ICursoService {
    Curso criarCurso(DadosCurso dados);

    Curso atualizarCurso(String cursoId, DadosCurso dados);

    Curso obterCurso(String cursoId);

    List<Curso> listarCursos();

    void associarDisciplinaAoCurso(String cursoId, String disciplinaId);

    void desassociarDisciplinaDoCurso(String cursoId, String disciplinaId);
}
