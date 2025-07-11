package com.iduff.iduff_server.service;

import java.util.List;
import java.util.UUID;

public interface IIntegracaoCEDERJService {
    void sincronizarAlunosCEDERJ();

    void enviarNotasParaCEDERJ(List<UUID> notaIds);

    String consultarDadosCEDERJ(String tipoConsulta, String parametro);
}
