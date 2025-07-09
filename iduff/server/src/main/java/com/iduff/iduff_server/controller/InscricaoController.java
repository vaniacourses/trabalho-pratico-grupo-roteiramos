package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IInscricaoService;
import com.iduff.iduff_server.entity.Solicitacao;
import com.iduff.iduff_server.entity.Inscricao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inscricoes")
@CrossOrigin(origins = "*")
public class InscricaoController {

    @Autowired
    private IInscricaoService inscricaoService;

    @PostMapping("/solicitacao")
    public ResponseEntity<Solicitacao> iniciarSolicitacaoInscricao(@RequestBody Map<String, String> dados) {
        try {
            String alunoId = dados.get("alunoId");
            String turmaId = dados.get("turmaId");

            Solicitacao solicitacao = inscricaoService.iniciarSolicitacaoInscricao(alunoId, turmaId);
            return ResponseEntity.ok(solicitacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/solicitacao/{solicitacaoId}/aprovar")
    public ResponseEntity<Void> aprovarSolicitacao(@PathVariable String solicitacaoId,
            @RequestBody Map<String, String> dados) {
        try {
            String coordenadorId = dados.get("coordenadorId");
            inscricaoService.aprovarSolicitacao(solicitacaoId, coordenadorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/solicitacao/{solicitacaoId}/reprovar")
    public ResponseEntity<Void> reprovarSolicitacao(@PathVariable String solicitacaoId,
            @RequestBody Map<String, String> dados) {
        try {
            String coordenadorId = dados.get("coordenadorId");
            String motivo = dados.get("motivo");
            inscricaoService.reprovarSolicitacao(solicitacaoId, coordenadorId, motivo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{solicitacaoId}/realizar")
    public ResponseEntity<Inscricao> realizarInscricao(@PathVariable String solicitacaoId) {
        try {
            Inscricao inscricao = inscricaoService.realizarInscricao(solicitacaoId);
            return ResponseEntity.ok(inscricao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{inscricaoId}")
    public ResponseEntity<Void> cancelarInscricao(@PathVariable String inscricaoId, @RequestParam String alunoId) {
        try {
            inscricaoService.cancelarInscricao(inscricaoId, alunoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<Inscricao>> obterInscricoesAluno(@PathVariable String alunoId) {
        List<Inscricao> inscricoes = inscricaoService.obterInscricoesAluno(alunoId);
        return ResponseEntity.ok(inscricoes);
    }

    @GetMapping("/{inscricaoId}")
    public ResponseEntity<Inscricao> obterDetalhesInscricao(@PathVariable String inscricaoId) {
        Inscricao inscricao = inscricaoService.obterDetalhesInscricao(inscricaoId);

        if (inscricao != null) {
            return ResponseEntity.ok(inscricao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}