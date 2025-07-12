package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.IInscricaoService;
import com.iduff.iduff_server.entity.*;
import com.iduff.iduff_server.enums.*;
import com.iduff.iduff_server.repository.UsuarioRepository;
import com.iduff.iduff_server.repository.InscricaoRepository;
import com.iduff.iduff_server.repository.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
    private final Map<UUID, Coordenador> coordenadores = new HashMap<>();

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @Override
    public Solicitacao iniciarSolicitacaoInscricao(UUID alunoId, UUID turmaId) {
        Usuario usuario = usuarioRepository.findById(alunoId).orElse(null);
        Turma turma = turmaRepository.findById(turmaId).orElse(null);

        if (usuario == null  || turma == null) {
            throw new IllegalArgumentException("Aluno ou turma não encontrados");
        }
        if (!(usuario instanceof Aluno)) {
            throw new IllegalArgumentException("Usuário não é um aluno");
        }

        Aluno aluno = (Aluno) usuario;
        Solicitacao solicitacao = new Solicitacao(TipoSolicitacao.INSCRICAO_DISCIPLINA, aluno, turma);
        solicitacao.setData(java.time.LocalDate.now());
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);

        inscricaoRepository.save(solicitacao);

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

    @Override
    public List<Solicitacao> obterSolicitacoesAluno(UUID alunoId) {
        return inscricaoRepository.findByAlunoId(alunoId);
    }
}
