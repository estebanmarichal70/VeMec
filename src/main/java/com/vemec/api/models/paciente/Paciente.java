package com.vemec.api.models.paciente;

import com.vemec.api.constants.Estado;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.vemec.VeMec;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;

import java.util.Date;
import java.util.List;

@Entity
public class Paciente {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private Estado estado;

    @ElementCollection
    private List<String> patologias;

    private String causa;
    @OneToMany
    private List<Reporte> historial;
    @OneToOne
    private Ubicacion ubicacion;
    @OneToOne
    private VeMec vemec;
    private Date fechaIngreso;
    private Date fechaEgreso;

    public Paciente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
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

    public List<Reporte> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Reporte> historial) {
        this.historial = historial;
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

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", estado=" + estado +
                ", causa='" + causa + '\'' +
                ", historial=" + historial +
                ", ubicacion=" + ubicacion +
                ", vemec=" + vemec +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaEgreso=" + fechaEgreso +
                '}';
    }
}
