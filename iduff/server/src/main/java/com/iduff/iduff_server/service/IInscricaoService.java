package com.iduff.iduff_server.service;

import com.iduff.iduff_server.entity.Solicitacao;
import com.iduff.iduff_server.entity.Inscricao;
import java.util.List;

public interface IInscricaoService {
    Solicitacao iniciarSolicitacaoInscricao(String alunoId, String turmaId);

    void aprovarSolicitacao(String solicitacaoId, String coordenadorId);

    void reprovarSolicitacao(String solicitacaoId, String coordenadorId, String motivo);

    Inscricao realizarInscricao(String solicitacaoId);

    void cancelarInscricao(String inscricaoId, String alunoId);

    List<Inscricao> obterInscricoesAluno(String alunoId);

    Inscricao obterDetalhesInscricao(String inscricaoId);
}
