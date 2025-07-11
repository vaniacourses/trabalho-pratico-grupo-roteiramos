package com.iduff.iduff_server.service;

import com.iduff.iduff_server.entity.Nota;
import com.iduff.iduff_server.entity.Comprovante;
import java.util.List;
import java.util.UUID;

public interface IDocumentoService {
    Nota lancarNota(UUID inscricaoId, double valorNota, String observacoes);

    Comprovante gerarHistoricoEscolar(UUID alunoId);

    Comprovante gerarComprovanteInscricao(UUID inscricaoId);

    Nota consultarNota(UUID notaId);

    List<Nota> consultarNotasAlunoDisciplina(UUID alunoId, UUID disciplinaId);
}
