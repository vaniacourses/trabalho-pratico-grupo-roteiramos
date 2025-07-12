package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.entity.Turma;
import com.iduff.iduff_server.service.ITurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private ITurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<Turma>> listarTurmasPorDisciplina(@RequestParam(required = false) UUID disciplinaId) {
        List<Turma> turmas;
        if (disciplinaId != null) {
            turmas = turmaService.listarPorDisciplina(disciplinaId);
        } else {
            turmas = turmaService.listarTurmas();
        }
        return ResponseEntity.ok(turmas);
    }
}
