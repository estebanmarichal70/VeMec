package com.vemec.api.models.vemec;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vemec.api.models.sala.Sala;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class VeMec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String marca;
    private String modelo;
    private Boolean estado;

    @JsonBackReference
    @ManyToOne
    private Sala sala;

    public VeMec() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public Sala getSala() {
        return sala;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "VeMec{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", estado=" + estado +
                ", sala=" + sala +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeMec veMec = (VeMec) o;
        return Objects.equals(id, veMec.id) &&
                Objects.equals(marca, veMec.marca) &&
                Objects.equals(modelo, veMec.modelo) &&
                Objects.equals(estado, veMec.estado) &&
                Objects.equals(sala, veMec.sala);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, modelo, estado, sala);
    }
}
