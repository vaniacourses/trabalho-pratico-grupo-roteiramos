package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.ITurmaService;
import com.iduff.iduff_server.repository.TurmaRepository;
import com.iduff.iduff_server.entity.Turma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class TurmaServiceImpl implements ITurmaService {

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public List<Turma> listarPorDisciplina(UUID disciplinaId) {
        return turmaRepository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }
}
