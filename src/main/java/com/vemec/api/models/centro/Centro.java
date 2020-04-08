package com.vemec.api.models.centro;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vemec.api.models.ubicacion.Ubicacion;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Centro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private String nombre;
    private String direccion;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ubicacion> ubicaciones;

    public Centro() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    // method to manage the bidirectional association
    public void addToUbicaciones(Ubicacion ubicacion) {
        this.ubicaciones.add(ubicacion);
        ubicacion.setCentro(this);
    }


    @Override
    public String toString() {
        return "Centro{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Centro centro = (Centro) o;
        return Objects.equals(id, centro.id) &&
                Objects.equals(codigo, centro.codigo) &&
                Objects.equals(nombre, centro.nombre) &&
                Objects.equals(direccion, centro.direccion) &&
                Objects.equals(ubicaciones, centro.ubicaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, direccion, ubicaciones);
    }
}
