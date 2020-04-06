package com.vemec.api.models.reporte;

import com.vemec.api.models.paciente.Paciente;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import java.sql.Date;

@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Float presionMaxima;
    private Float presionMinima;
    private Float volGas;
    private Float frecGas;
    private Float mezcla;
    private Float humedadAire;
    private Float tempEntrada;
    private Float tempSalida;
    private Float presionEntrada;
    private Float presionSalida;
    private Date time;
    @ManyToOne
    private Paciente paciente;

    public Reporte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPresionMaxima() {
        return presionMaxima;
    }

    public void setPresionMaxima(Float presionMaxima) {
        this.presionMaxima = presionMaxima;
    }

    public Float getPresionMinima() {
        return presionMinima;
    }

    public void setPresionMinima(Float presionMinima) {
        this.presionMinima = presionMinima;
    }

    public Float getVolGas() {
        return volGas;
    }

    public void setVolGas(Float volGas) {
        this.volGas = volGas;
    }

    public Float getFrecGas() {
        return frecGas;
    }

    public void setFrecGas(Float frecGas) {
        this.frecGas = frecGas;
    }

    public Float getMezcla() {
        return mezcla;
    }

    public void setMezcla(Float mezcla) {
        this.mezcla = mezcla;
    }

    public Float getHumedadAire() {
        return humedadAire;
    }

    public void setHumedadAire(Float humedadAire) {
        this.humedadAire = humedadAire;
    }

    public Float getTempEntrada() {
        return tempEntrada;
    }

    public void setTempEntrada(Float tempEntrada) {
        this.tempEntrada = tempEntrada;
    }

    public Float getTempSalida() {
        return tempSalida;
    }

    public void setTempSalida(Float tempSalida) {
        this.tempSalida = tempSalida;
    }

    public Float getPresionEntrada() {
        return presionEntrada;
    }

    public void setPresionEntrada(Float presionEntrada) {
        this.presionEntrada = presionEntrada;
    }

    public Float getPresionSalida() {
        return presionSalida;
    }

    public void setPresionSalida(Float presionSalida) {
        this.presionSalida = presionSalida;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                ", presionMaxima=" + presionMaxima +
                ", presionMinima=" + presionMinima +
                ", volGas=" + volGas +
                ", frecGas=" + frecGas +
                ", mezcla=" + mezcla +
                ", humedadAire=" + humedadAire +
                ", tempEntrada=" + tempEntrada +
                ", tempSalida=" + tempSalida +
                ", presionEntrada=" + presionEntrada +
                ", presionSalida=" + presionSalida +
                ", time=" + time +
                ", paciente=" + paciente +
                '}';
    }
}
