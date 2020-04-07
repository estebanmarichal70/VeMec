package com.vemec.api.models.ubicacion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.centro.Centro;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer capacidad;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Centro centro;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<VeMec> vemecs;

    public Ubicacion() {
    }

    public void addToVeMecs(VeMec vemec) {
        this.vemecs.add(vemec);
        vemec.setUbicacion(this);
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

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Centro getCentro() {
        return centro;
    }

    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    public List<VeMec> getVemecs() {
        return vemecs;
    }

    public void setVemecs(List<VeMec> vemecs) {
        this.vemecs = vemecs;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", capacidad=" + capacidad +
                ", centro=" + centro +
                ", vemecs=" + vemecs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ubicacion ubicacion = (Ubicacion) o;
        return Objects.equals(id, ubicacion.id) &&
                Objects.equals(nombre, ubicacion.nombre) &&
                Objects.equals(capacidad, ubicacion.capacidad) &&
                Objects.equals(centro, ubicacion.centro) &&
                Objects.equals(vemecs, ubicacion.vemecs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, capacidad, centro, vemecs);
    }
}
