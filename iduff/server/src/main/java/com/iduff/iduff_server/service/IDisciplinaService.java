package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosDisciplina;
import com.iduff.iduff_server.entity.Disciplina;
import java.util.List;

public interface IDisciplinaService {
    Disciplina criarDisciplina(DadosDisciplina dados);

    Disciplina atualizarDisciplina(String disciplinaId, DadosDisciplina dados);

    Disciplina obterDisciplina(String disciplinaId);

    List<Disciplina> listarDisciplinas();

    void associarPreRequisito(String disciplinaId, String preRequisitoId);

    void desassociarPreRequisito(String disciplinaId, String preRequisitoId);
}
