package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.IInscricaoService;
import com.iduff.iduff_server.entity.*;
import com.iduff.iduff_server.enums.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

@Service
public class InscricaoServiceImpl implements IInscricaoService {

    // In-memory storage for demo purposes
    private final Map<UUID, Solicitacao> solicitacoes = new HashMap<>();
    private final Map<UUID, Inscricao> inscricoes = new HashMap<>();
    private final Map<UUID, Aluno> alunos = new HashMap<>();
    private final Map<UUID, Turma> turmas = new HashMap<>();
    private final Map<UUID, Coordenador> coordenadores = new HashMap<>();

    @Override
    public Solicitacao iniciarSolicitacaoInscricao(UUID alunoId, UUID turmaId) {
        Aluno aluno = alunos.get(alunoId);
        Turma turma = turmas.get(turmaId);

        if (aluno == null || turma == null) {
            throw new IllegalArgumentException("Aluno ou turma não encontrados");
        }

        Solicitacao solicitacao = new Solicitacao(TipoSolicitacao.INSCRICAO_DISCIPLINA, aluno, turma);
        solicitacoes.put(solicitacao.getId(), solicitacao);

        return solicitacao;
    }

    @Override
    public void aprovarSolicitacao(UUID solicitacaoId, UUID coordenadorId) {
        Solicitacao solicitacao = solicitacoes.get(solicitacaoId);
        Coordenador coordenador = coordenadores.get(coordenadorId);

        if (solicitacao != null && coordenador != null) {
            solicitacao.aprovar(coordenador);
        }
    }

    @Override
    public void reprovarSolicitacao(UUID solicitacaoId, UUID coordenadorId, String motivo) {
        Solicitacao solicitacao = solicitacoes.get(solicitacaoId);
        Coordenador coordenador = coordenadores.get(coordenadorId);

        if (solicitacao != null && coordenador != null) {
            solicitacao.reprovar(coordenador, motivo);
        }
    }

    @Override
    public Inscricao realizarInscricao(UUID solicitacaoId) {
        Solicitacao solicitacao = solicitacoes.get(solicitacaoId);

        if (solicitacao == null || solicitacao.getStatus() != StatusSolicitacao.APROVADA) {
            throw new IllegalStateException("Solicitação não aprovada");
        }

        Inscricao inscricao = new Inscricao("2024.1", solicitacao.getAluno(), solicitacao.getTurma(), solicitacao);
        inscricoes.put(inscricao.getId(), inscricao);

        return inscricao;
    }

    @Override
    public void cancelarInscricao(UUID inscricaoId, UUID alunoId) {
        Inscricao inscricao = inscricoes.get(inscricaoId);

        if (inscricao != null && inscricao.getAluno().getId().equals(alunoId)) {
            inscricao.cancelar();
        }
    }

    @Override
    public List<Inscricao> obterInscricoesAluno(UUID alunoId) {
        List<Inscricao> resultado = new ArrayList<>();

        for (Inscricao inscricao : inscricoes.values()) {
            if (inscricao.getAluno().getId().equals(alunoId)) {
                resultado.add(inscricao);
            }
        }

        return resultado;
    }

    @Override
    public Inscricao obterDetalhesInscricao(UUID inscricaoId) {
        return inscricoes.get(inscricaoId);
    }
}
