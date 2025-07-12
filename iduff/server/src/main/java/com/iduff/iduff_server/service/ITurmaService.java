package com.iduff.iduff_server.service;

import com.iduff.iduff_server.entity.Turma;
import java.util.List;
import java.util.UUID;

public interface ITurmaService {
    List<Turma> listarPorDisciplina(UUID disciplinaId);
    List<Turma> listarTurmas();
}
