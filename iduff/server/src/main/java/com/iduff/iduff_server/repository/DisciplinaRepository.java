package com.iduff.iduff_server.repository;

import com.iduff.iduff_server.entity.Disciplina;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, UUID> {
    List<Disciplina> findByNomeContainingIgnoreCaseOrCodigoContainingIgnoreCase(String nome, String codigo);
}
