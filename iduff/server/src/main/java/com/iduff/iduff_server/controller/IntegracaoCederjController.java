package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IIntegracaoCEDERJService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/integracao-cederj")
@CrossOrigin(origins = "*")
public class IntegracaoCederjController {

    @Autowired
    private IIntegracaoCEDERJService integracaoCEDERJService;

    @PostMapping("/sincronizar-alunos")
    public ResponseEntity<Map<String, String>> sincronizarAlunosCEDERJ() {
        try {
            integracaoCEDERJService.sincronizarAlunosCEDERJ();
            return ResponseEntity.ok(Map.of("message", "Sincronização realizada com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro na sincronização: " + e.getMessage()));
        }
    }

    @PostMapping("/enviar-notas")
    public ResponseEntity<Map<String, String>> enviarNotasParaCEDERJ(@RequestBody Map<String, List<String>> dados) {
        try {
            List<String> notaIdsStr = dados.get("notaIds");
            List<UUID> notaIds = notaIdsStr.stream().map(UUID::fromString).toList();
            integracaoCEDERJService.enviarNotasParaCEDERJ(notaIds);
            return ResponseEntity.ok(Map.of("message", "Notas enviadas com sucesso"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro no envio de notas: " + e.getMessage()));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Map<String, String>> consultarDadosCEDERJ(@RequestParam String tipoConsulta,
            @RequestParam String parametro) {
        try {
            String resultado = integracaoCEDERJService.consultarDadosCEDERJ(tipoConsulta, parametro);
            return ResponseEntity.ok(Map.of("resultado", resultado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Erro na consulta: " + e.getMessage()));
        }
    }
}