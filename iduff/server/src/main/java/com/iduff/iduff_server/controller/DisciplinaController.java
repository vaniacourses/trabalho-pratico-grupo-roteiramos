package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IDisciplinaService;
import com.iduff.iduff_server.dto.DadosDisciplina;
import com.iduff.iduff_server.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/disciplinas")
@CrossOrigin(origins = "*")
public class DisciplinaController {

    @Autowired
    private IDisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<Disciplina> criarDisciplina(@RequestBody DadosDisciplina dados) {
        try {
            Disciplina disciplina = disciplinaService.criarDisciplina(dados);
            return ResponseEntity.ok(disciplina);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{disciplinaId}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable String disciplinaId, @RequestBody DadosDisciplina dados) {
        try {
            UUID id = UUID.fromString(disciplinaId);
            Disciplina disciplina = disciplinaService.atualizarDisciplina(id, dados);
            if (disciplina != null) {
                return ResponseEntity.ok(disciplina);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{disciplinaId}")
    public ResponseEntity<Disciplina> obterDisciplina(@PathVariable String disciplinaId) {
        UUID id = UUID.fromString(disciplinaId);
        Disciplina disciplina = disciplinaService.obterDisciplina(id);
        
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarDisciplinas(@RequestParam(required = false) String busca) {
        List<Disciplina> disciplinas;
        if (busca != null && !busca.isEmpty()) {
            disciplinas = disciplinaService.buscarPorNomeOuCodigo(busca);
        } else {
            disciplinas = disciplinaService.listarDisciplinas();
        }
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping("/{disciplinaId}/pre-requisitos")
    public ResponseEntity<Void> associarPreRequisito(@PathVariable String disciplinaId, @RequestBody Map<String, String> dados) {
        try {
            UUID id = UUID.fromString(disciplinaId);
            UUID preRequisitoId = UUID.fromString(dados.get("preRequisitoId"));
            disciplinaService.associarPreRequisito(id, preRequisitoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{disciplinaId}/pre-requisitos/{preRequisitoId}")
    public ResponseEntity<Void> desassociarPreRequisito(@PathVariable String disciplinaId, @PathVariable String preRequisitoId) {
        try {
            UUID id = UUID.fromString(disciplinaId);
            UUID preId = UUID.fromString(preRequisitoId);
            disciplinaService.desassociarPreRequisito(id, preId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}