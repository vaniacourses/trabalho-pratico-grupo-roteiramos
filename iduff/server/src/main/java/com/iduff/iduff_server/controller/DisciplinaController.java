package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IDisciplinaService;
import com.iduff.iduff_server.dto.DadosDisciplina;
import com.iduff.iduff_server.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
            Disciplina disciplina = disciplinaService.atualizarDisciplina(disciplinaId, dados);
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
        Disciplina disciplina = disciplinaService.obterDisciplina(disciplinaId);
        
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarDisciplinas() {
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping("/{disciplinaId}/pre-requisitos")
    public ResponseEntity<Void> associarPreRequisito(@PathVariable String disciplinaId, @RequestBody Map<String, String> dados) {
        try {
            String preRequisitoId = dados.get("preRequisitoId");
            disciplinaService.associarPreRequisito(disciplinaId, preRequisitoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{disciplinaId}/pre-requisitos/{preRequisitoId}")
    public ResponseEntity<Void> desassociarPreRequisito(@PathVariable String disciplinaId, @PathVariable String preRequisitoId) {
        try {
            disciplinaService.desassociarPreRequisito(disciplinaId, preRequisitoId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}