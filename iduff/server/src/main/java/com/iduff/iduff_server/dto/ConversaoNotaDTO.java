package com.iduff.iduff_server.dto;

public class ConversaoNotaDTO {
    private String cederjDisciplina;
    private Double cederjNota;
    private String uffDisciplina;
    private Double uffNota;

    // Getters and setters
    public String getCederjDisciplina() {
        return cederjDisciplina;
    }

    public void setCederjDisciplina(String cederjDisciplina) {
        this.cederjDisciplina = cederjDisciplina;
    }

    public Double getCederjNota() {
        return cederjNota;
    }

    public void setCederjNota(Double cederjNota) {
        this.cederjNota = cederjNota;
    }

    public String getUffDisciplina() {
        return uffDisciplina;
    }

    public void setUffDisciplina(String uffDisciplina) {
        this.uffDisciplina = uffDisciplina;
    }

    public Double getUffNota() {
        return uffNota;
    }

    public void setUffNota(Double uffNota) {
        this.uffNota = uffNota;
    }
}
