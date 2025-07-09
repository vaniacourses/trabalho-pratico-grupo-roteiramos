package com.iduff.iduff_server.service;

import java.util.List;

public interface IIntegracaoCEDERJService {
    void sincronizarAlunosCEDERJ();

    void enviarNotasParaCEDERJ(List<String> notaIds);

    String consultarDadosCEDERJ(String tipoConsulta, String parametro);
}
