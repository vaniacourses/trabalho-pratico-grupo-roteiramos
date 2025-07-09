package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.ICursoService;
import com.iduff.iduff_server.dto.DadosCurso;
import com.iduff.iduff_server.entity.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private ICursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody DadosCurso dados) {
        try {
            Curso curso = cursoService.criarCurso(dados);
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cursoId}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable String cursoId, @RequestBody DadosCurso dados) {
        try {
            Curso curso = cursoService.atualizarCurso(cursoId, dados);
            if (curso != null) {
                return ResponseEntity.ok(curso);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{cursoId}")
    public ResponseEntity<Curso> obterCurso(@PathVariable String cursoId) {
        Curso curso = cursoService.obterCurso(cursoId);

        if (curso != null) {
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @PostMapping("/{cursoId}/disciplinas")
    public ResponseEntity<Void> associarDisciplinaAoCurso(@PathVariable String cursoId,
            @RequestBody Map<String, String> dados) {
        try {
            String disciplinaId = dados.get("disciplinaId");
            cursoService.associarDisciplinaAoCurso(cursoId, disciplinaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{cursoId}/disciplinas/{disciplinaId}")
    public ResponseEntity<Void> desassociarDisciplinaDoCurso(@PathVariable String cursoId,
            @PathVariable String disciplinaId) {
        try {
            cursoService.desassociarDisciplinaDoCurso(cursoId, disciplinaId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}