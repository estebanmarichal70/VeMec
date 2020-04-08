package com.vemec.api.models.ingreso;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vemec.api.constants.Estado;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.paciente.Paciente;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ingreso {
    @Id
    private Integer id;
    private Estado estado;
    private String causa;
    @OneToOne
    private Ubicacion ubicacion;
    @OneToOne
    private VeMec vemec;
    private Date fechaIngreso;
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
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

    public void addToReportes(Reporte reporte) {
        this.historial.add(reporte);
        reporte.setIngreso(this);
    }
    @Override
    public String toString() {
        return "Ingreso{" +
                "id=" + id +
                ", estado=" + estado +
                ", causa='" + causa + '\'' +
                ", ubicacion=" + ubicacion +
                ", vemec=" + vemec +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaEgreso=" + fechaEgreso +
                ", paciente=" + paciente +
                ", historial=" + historial +
                '}';
    }
}
