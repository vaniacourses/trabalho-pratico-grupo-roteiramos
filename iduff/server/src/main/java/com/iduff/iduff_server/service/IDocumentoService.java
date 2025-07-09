package com.iduff.iduff_server.service;

import com.iduff.iduff_server.entity.Nota;
import com.iduff.iduff_server.entity.Comprovante;
import java.util.List;

public interface IDocumentoService {
    Nota lancarNota(String inscricaoId, double valorNota, String observacoes);

    Comprovante gerarHistoricoEscolar(String alunoId);

    Comprovante gerarComprovanteInscricao(String inscricaoId);

    Nota consultarNota(String notaId);

    List<Nota> consultarNotasAlunoDisciplina(String alunoId, String disciplinaId);
}
