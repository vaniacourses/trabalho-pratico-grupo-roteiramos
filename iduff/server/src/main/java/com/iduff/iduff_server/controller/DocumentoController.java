package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IDocumentoService;
import com.iduff.iduff_server.entity.Nota;
import com.iduff.iduff_server.entity.Comprovante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/documentos")
@CrossOrigin(origins = "*")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @PostMapping("/notas")
    public ResponseEntity<Nota> lancarNota(@RequestBody Map<String, Object> dados) {
        try {
            UUID inscricaoId = UUID.fromString((String) dados.get("inscricaoId"));
            double valorNota = ((Number) dados.get("valorNota")).doubleValue();
            String observacoes = (String) dados.get("observacoes");

            Nota nota = documentoService.lancarNota(inscricaoId, valorNota, observacoes);
            return ResponseEntity.ok(nota);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/historico/{alunoId}")
    public ResponseEntity<Comprovante> gerarHistoricoEscolar(@PathVariable String alunoId) {
        try {
            UUID id = UUID.fromString(alunoId);
            Comprovante comprovante = documentoService.gerarHistoricoEscolar(id);
            return ResponseEntity.ok(comprovante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/comprovante-inscricao/{inscricaoId}")
    public ResponseEntity<Comprovante> gerarComprovanteInscricao(@PathVariable String inscricaoId) {
        try {
            UUID id = UUID.fromString(inscricaoId);
            Comprovante comprovante = documentoService.gerarComprovanteInscricao(id);
            return ResponseEntity.ok(comprovante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/notas/{notaId}")
    public ResponseEntity<Nota> consultarNota(@PathVariable String notaId) {
        UUID id = UUID.fromString(notaId);
        Nota nota = documentoService.consultarNota(id);

        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notas/aluno/{alunoId}/disciplina/{disciplinaId}")
    public ResponseEntity<List<Nota>> consultarNotasAlunoDisciplina(@PathVariable String alunoId,
            @PathVariable String disciplinaId) {
        UUID alunoUUID = UUID.fromString(alunoId);
        UUID disciplinaUUID = UUID.fromString(disciplinaId);
        List<Nota> notas = documentoService.consultarNotasAlunoDisciplina(alunoUUID, disciplinaUUID);
        return ResponseEntity.ok(notas);
    }
}