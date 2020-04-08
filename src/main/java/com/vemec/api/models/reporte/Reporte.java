package com.vemec.api.models.reporte;

import com.vemec.api.models.ingreso.Ingreso;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import java.sql.Date;
import java.util.Objects;

@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double presionMaxima;
    private Double presionMinima;
    private Double volGas;
    private Double frecGas;
    private Double mezcla;
    private Double humedadAire;
    private Double tempEntrada;
    private Double tempSalida;
    private Double presionEntrada;
    private Double presionSalida;
    private Date time;
    @ManyToOne
    private Ingreso ingreso;

    public Reporte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPresionMaxima() {
        return presionMaxima;
    }

    public void setPresionMaxima(Double presionMaxima) {
        this.presionMaxima = presionMaxima;
    }

    public Double getPresionMinima() {
        return presionMinima;
    }

    public void setPresionMinima(Double presionMinima) {
        this.presionMinima = presionMinima;
    }

    public Double getVolGas() {
        return volGas;
    }

    public void setVolGas(Double volGas) {
        this.volGas = volGas;
    }

    public Double getFrecGas() {
        return frecGas;
    }

    public void setFrecGas(Double frecGas) {
        this.frecGas = frecGas;
    }

    public Double getMezcla() {
        return mezcla;
    }

    public void setMezcla(Double mezcla) {
        this.mezcla = mezcla;
    }

    public Double getHumedadAire() {
        return humedadAire;
    }

    public void setHumedadAire(Double humedadAire) {
        this.humedadAire = humedadAire;
    }

    public Double getTempEntrada() {
        return tempEntrada;
    }

    public void setTempEntrada(Double tempEntrada) {
        this.tempEntrada = tempEntrada;
    }

    public Double getTempSalida() {
        return tempSalida;
    }

    public void setTempSalida(Double tempSalida) {
        this.tempSalida = tempSalida;
    }

    public Double getPresionEntrada() {
        return presionEntrada;
    }

    public void setPresionEntrada(Double presionEntrada) {
        this.presionEntrada = presionEntrada;
    }

    public Double getPresionSalida() {
        return presionSalida;
    }

    public void setPresionSalida(Double presionSalida) {
        this.presionSalida = presionSalida;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
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
                ", paciente=" + ingreso +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reporte reporte = (Reporte) o;
        return Objects.equals(id, reporte.id) &&
                Objects.equals(presionMaxima, reporte.presionMaxima) &&
                Objects.equals(presionMinima, reporte.presionMinima) &&
                Objects.equals(volGas, reporte.volGas) &&
                Objects.equals(frecGas, reporte.frecGas) &&
                Objects.equals(mezcla, reporte.mezcla) &&
                Objects.equals(humedadAire, reporte.humedadAire) &&
                Objects.equals(tempEntrada, reporte.tempEntrada) &&
                Objects.equals(tempSalida, reporte.tempSalida) &&
                Objects.equals(presionEntrada, reporte.presionEntrada) &&
                Objects.equals(presionSalida, reporte.presionSalida) &&
                Objects.equals(time, reporte.time) &&
                Objects.equals(ingreso, reporte.ingreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, presionMaxima, presionMinima, volGas, frecGas, mezcla, humedadAire, tempEntrada, tempSalida, presionEntrada, presionSalida, time, ingreso);
    }
}
