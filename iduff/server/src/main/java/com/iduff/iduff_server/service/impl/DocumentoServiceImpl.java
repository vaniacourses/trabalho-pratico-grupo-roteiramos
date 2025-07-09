package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.IDocumentoService;
import com.iduff.iduff_server.entity.*;
import com.iduff.iduff_server.enums.TipoComprovante;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    // In-memory storage for demo purposes
    private final Map<String, Nota> notas = new HashMap<>();
    private final Map<String, Comprovante> comprovantes = new HashMap<>();
    private final Map<String, Inscricao> inscricoes = new HashMap<>();
    private final Map<String, Aluno> alunos = new HashMap<>();

    @Override
    public Nota lancarNota(String inscricaoId, double valorNota, String observacoes) {
        Inscricao inscricao = inscricoes.get(inscricaoId);

        if (inscricao == null) {
            throw new IllegalArgumentException("Inscrição não encontrada");
        }

        Nota nota = new Nota(valorNota, observacoes, inscricao);

        if (!nota.validarValor()) {
            throw new IllegalArgumentException("Valor da nota inválido");
        }

        notas.put(nota.getId(), nota);
        return nota;
    }

    @Override
    public Comprovante gerarHistoricoEscolar(String alunoId) {
        Aluno aluno = alunos.get(alunoId);

        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado");
        }

        // Generate historical transcript content
        String conteudo = "HISTÓRICO ESCOLAR\n\nAluno: " + aluno.getNome() + "\nMatrícula: " + aluno.getMatricula()
                + "\n\n";

        Comprovante comprovante = new Comprovante(TipoComprovante.HISTORICO_ESCOLAR, conteudo);
        comprovante.setAluno(aluno);

        comprovantes.put(comprovante.getId(), comprovante);
        return comprovante;
    }

    @Override
    public Comprovante gerarComprovanteInscricao(String inscricaoId) {
        Inscricao inscricao = inscricoes.get(inscricaoId);

        if (inscricao == null) {
            throw new IllegalArgumentException("Inscrição não encontrada");
        }

        String conteudo = "COMPROVANTE DE INSCRIÇÃO\n\nAluno: " + inscricao.getAluno().getNome() +
                "\nDisciplina: " + inscricao.getTurma().getDisciplina().getNome() +
                "\nTurma: " + inscricao.getTurma().getNome() +
                "\nPeríodo: " + inscricao.getPeriodo();

        Comprovante comprovante = new Comprovante(TipoComprovante.COMPROVANTE_INSCRICAO, conteudo);
        comprovante.setInscricao(inscricao);

        comprovantes.put(comprovante.getId(), comprovante);
        return comprovante;
    }

    @Override
    public Nota consultarNota(String notaId) {
        return notas.get(notaId);
    }

    @Override
    public List<Nota> consultarNotasAlunoDisciplina(String alunoId, String disciplinaId) {
        List<Nota> resultado = new ArrayList<>();

        for (Nota nota : notas.values()) {
            if (nota.getInscricao().getAluno().getId().equals(alunoId) &&
                    nota.getInscricao().getTurma().getDisciplina().getId().equals(disciplinaId)) {
                resultado.add(nota);
            }
        }

        return resultado;
    }
}
