package com.iduff.iduff_server.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iduff.iduff_server.entity.Solicitacao;

public interface InscricaoRepository extends JpaRepository<Solicitacao, UUID> {
    List<Solicitacao> findByAlunoId(UUID alunoId);
}
