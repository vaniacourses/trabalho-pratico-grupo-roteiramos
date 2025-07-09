package com.iduff.iduff_server.entity;

import com.iduff.iduff_server.enums.DiaSemana;
import java.time.LocalTime;

public class Horario {
    private DiaSemana diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    // Constructors
    public Horario() {
    }

    public Horario(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim) {
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    // Business methods
    public boolean verificarConflito(Horario outroHorario) {
        if (!this.diaSemana.equals(outroHorario.diaSemana)) {
            return false;
        }

        return !(this.horaFim.isBefore(outroHorario.horaInicio) ||
                this.horaInicio.isAfter(outroHorario.horaFim));
    }

    // Getters and Setters
    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}
