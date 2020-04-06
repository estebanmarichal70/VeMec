package com.vemec.api.models.paciente;

import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ingreso.Ingreso;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Paciente {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    @ElementCollection
    private List<String> patologias;
    @OneToMany
    private List<Ingreso> ingresos;

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

    public List<String> getPatologias() {
        return patologias;
    }

    public void setPatologias(List<String> patologias) {
        this.patologias = patologias;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", patologias=" + patologias +
                ", ingresos=" + ingresos +
                '}';
    }
}
