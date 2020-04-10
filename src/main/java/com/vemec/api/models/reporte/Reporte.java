package com.vemec.api.models.reporte;

import com.vemec.api.models.ingreso.Ingreso;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;
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

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;


    @ManyToOne
    private Ingreso ingreso;
    private String unidadPresion;
    private String unidadTemp;
    private String unidadHumedad;
    private String unidadFrecuencia;
    private String unidadVolumen;

    public Reporte() {
    }

    public String getUnidadPresion() {
        return unidadPresion;
    }

    public void setUnidadPresion(String unidadPresion) {
        this.unidadPresion = unidadPresion;
    }

    public String getUnidadTemp() {
        return unidadTemp;
    }

    public void setUnidadTemp(String unidadTemp) {
        this.unidadTemp = unidadTemp;
    }

    public String getUnidadHumedad() {
        return unidadHumedad;
    }

    public void setUnidadHumedad(String unidadHumedad) {
        this.unidadHumedad = unidadHumedad;
    }

    public String getUnidadFrecuencia() {
        return unidadFrecuencia;
    }

    public void setUnidadFrecuencia(String unidadFrecuencia) {
        this.unidadFrecuencia = unidadFrecuencia;
    }

    public String getUnidadVolumen() {
        return unidadVolumen;
    }

    public void setUnidadVolumen(String unidadVolumen) {
        this.unidadVolumen = unidadVolumen;
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
                ", ingreso=" + ingreso +
                ", unidadPresion='" + unidadPresion + '\'' +
                ", unidadTemp='" + unidadTemp + '\'' +
                ", unidadHumedad='" + unidadHumedad + '\'' +
                ", unidadFrecuencia='" + unidadFrecuencia + '\'' +
                ", unidadVolumen='" + unidadVolumen + '\'' +
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
                Objects.equals(ingreso, reporte.ingreso) &&
                Objects.equals(unidadPresion, reporte.unidadPresion) &&
                Objects.equals(unidadTemp, reporte.unidadTemp) &&
                Objects.equals(unidadHumedad, reporte.unidadHumedad) &&
                Objects.equals(unidadFrecuencia, reporte.unidadFrecuencia) &&
                Objects.equals(unidadVolumen, reporte.unidadVolumen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, presionMaxima, presionMinima, volGas, frecGas, mezcla, humedadAire, tempEntrada, tempSalida, presionEntrada, presionSalida, time, ingreso, unidadPresion, unidadTemp, unidadHumedad, unidadFrecuencia, unidadVolumen);
    }
}
