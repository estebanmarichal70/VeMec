package com.vemec.api.models.sala;
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
public class Sala {
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

    public Sala() {
    }

    public void addToVeMecs(VeMec vemec) {
        this.vemecs.add(vemec);
        vemec.setSala(this);
    }
    public void removeFromVemec(VeMec vemec){
        this.vemecs.remove(vemec);
        vemec.setSala(null);
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
        return "Sala{" +
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
        Sala sala = (Sala) o;
        return Objects.equals(id, sala.id) &&
                Objects.equals(nombre, sala.nombre) &&
                Objects.equals(capacidad, sala.capacidad) &&
                Objects.equals(centro, sala.centro) &&
                Objects.equals(vemecs, sala.vemecs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, capacidad, centro, vemecs);
    }
}
