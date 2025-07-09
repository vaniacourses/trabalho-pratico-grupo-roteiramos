package com.iduff.iduff_server.dto;

import java.util.List;

public class HistoricoDTO {
    private Long alunoId;
    private List<DisciplinaHistoricoDTO> disciplinas;

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public List<DisciplinaHistoricoDTO> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<DisciplinaHistoricoDTO> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public static class DisciplinaHistoricoDTO {
        private String nome;
        private Double nota;
        private String instituicao;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Double getNota() {
            return nota;
        }

        public void setNota(Double nota) {
            this.nota = nota;
        }

        public String getInstituicao() {
            return instituicao;
        }

        public void setInstituicao(String instituicao) {
            this.instituicao = instituicao;
        }
    }
}
