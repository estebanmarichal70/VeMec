package com.vemec.api.models.ingreso;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vemec.api.constants.Estado;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.paciente.Paciente;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Estado estado;
    private String causa;
    @OneToOne(cascade = CascadeType.ALL)
    private Sala sala;
    @OneToOne(cascade = CascadeType.ALL)
    private VeMec vemec;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEgreso;

    @JsonBackReference
    @ManyToOne
    private Paciente paciente;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Reporte> historial;

    public Ingreso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public VeMec getVemec() {
        return vemec;
    }

    public void setVemec(VeMec vemec) {
        this.vemec = vemec;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Reporte> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Reporte> historial) {
        this.historial = historial;
    }

    public void addToHistorial(Reporte reporte) {
        this.historial.add(reporte);
        reporte.setIngreso(this);
    }
    public void removeFromReportes(Reporte reporte){
        this.historial.remove(reporte);
        reporte.setIngreso(null);
    }

    @Override
    public String toString() {
        return "Ingreso{" +
                "id=" + id +
                ", estado=" + estado +
                ", causa='" + causa + '\'' +
                ", sala=" + sala +
                ", vemec=" + vemec +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaEgreso=" + fechaEgreso +
                ", paciente=" + paciente +
                ", historial=" + historial +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingreso ingreso = (Ingreso) o;
        return Objects.equals(id, ingreso.id) &&
                estado == ingreso.estado &&
                Objects.equals(causa, ingreso.causa) &&
                Objects.equals(sala, ingreso.sala) &&
                Objects.equals(vemec, ingreso.vemec) &&
                Objects.equals(fechaIngreso, ingreso.fechaIngreso) &&
                Objects.equals(fechaEgreso, ingreso.fechaEgreso) &&
                Objects.equals(paciente, ingreso.paciente) &&
                Objects.equals(historial, ingreso.historial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado, causa, sala, vemec, fechaIngreso, fechaEgreso, paciente, historial);
    }
}
