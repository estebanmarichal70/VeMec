package com.vemec.api.models.vemec;
import com.vemec.api.models.ubicacion.Ubicacion;

import javax.persistence.*;

@Entity
public class VeMec {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String marca;
    private String modelo;
    private Boolean estado;
    @ManyToOne
    private Ubicacion ubicacion;

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

    public Ubicacion getUbicacion() {
        return ubicacion;
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

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "VeMec{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", estado=" + estado +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
