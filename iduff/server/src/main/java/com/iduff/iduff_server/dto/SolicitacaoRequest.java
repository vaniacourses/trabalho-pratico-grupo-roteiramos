package com.iduff.iduff_server.dto;

import java.util.UUID;

public class SolicitacaoRequest {
    private UUID alunoId;
    private UUID turmaId;

    public UUID getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(UUID alunoId) {
        this.alunoId = alunoId;
    }

    public UUID getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(UUID turmaId) {
        this.turmaId = turmaId;
    }
}
