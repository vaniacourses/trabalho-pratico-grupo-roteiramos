package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosDisciplina;
import com.iduff.iduff_server.entity.Disciplina;
import java.util.List;
import java.util.UUID;

public interface IDisciplinaService {
    Disciplina criarDisciplina(DadosDisciplina dados);

    Disciplina atualizarDisciplina(UUID disciplinaId, DadosDisciplina dados);

    Disciplina obterDisciplina(UUID disciplinaId);

    List<Disciplina> listarDisciplinas();

    void associarPreRequisito(UUID disciplinaId, UUID preRequisitoId);

    void desassociarPreRequisito(UUID disciplinaId, UUID preRequisitoId);

    List<Disciplina> buscarPorNomeOuCodigo(String busca);
}
