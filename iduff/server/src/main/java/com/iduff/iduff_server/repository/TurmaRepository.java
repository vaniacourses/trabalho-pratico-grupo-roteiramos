package com.iduff.iduff_server.repository;

import com.iduff.iduff_server.entity.Turma;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, UUID> {
    List<Turma> findByDisciplinaId(UUID disciplinaId);
}
