package com.iduff.iduff_server.service;

import com.iduff.iduff_server.entity.Solicitacao;
import com.iduff.iduff_server.entity.Inscricao;
import java.util.List;
import java.util.UUID;

public interface IInscricaoService {
    Solicitacao iniciarSolicitacaoInscricao(UUID alunoId, UUID turmaId);

    void aprovarSolicitacao(UUID solicitacaoId, UUID coordenadorId);

    void reprovarSolicitacao(UUID solicitacaoId, UUID coordenadorId, String motivo);

    Inscricao realizarInscricao(UUID solicitacaoId);

    void cancelarInscricao(UUID inscricaoId, UUID alunoId);

    List<Inscricao> obterInscricoesAluno(UUID alunoId);

    Inscricao obterDetalhesInscricao(UUID inscricaoId);
}
