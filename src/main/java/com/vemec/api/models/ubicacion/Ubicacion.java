package com.vemec.api.models.ubicacion;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.centro.Centro;

import javax.persistence.*;

import java.util.List;

@Entity
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private Integer capacidad;
    @ManyToOne
    private Centro centro;
    @OneToMany
    private List<VeMec> vemecs;

    public Ubicacion() {
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
}
