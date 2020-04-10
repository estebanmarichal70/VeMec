package com.vemec.api.models.paciente;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Paciente {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;

    @ManyToOne
    private PatologiasWrapper patologias;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
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

    public PatologiasWrapper getPatologias() {
        return patologias;
    }

    public void setPatologias(PatologiasWrapper patologias) {
        this.patologias = patologias;
    }

    public List<Ingreso> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
    }

    // method to manage the bidirectional association
    public void addToIngresos(Ingreso ingreso) {
        this.ingresos.add(ingreso);
        ingreso.setPaciente(this);
    }
    public void removeFromIngresos(Ingreso ingreso){
        this.ingresos.remove(ingreso);
        ingreso.setPaciente(null);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id) &&
                Objects.equals(nombre, paciente.nombre) &&
                Objects.equals(apellido, paciente.apellido) &&
                Objects.equals(edad, paciente.edad) &&
                Objects.equals(patologias, paciente.patologias) &&
                Objects.equals(ingresos, paciente.ingresos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, edad, patologias, ingresos);
    }
}
