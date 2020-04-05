package com.vemec.api.models;
import javax.persistence.*;
import java.util.List;

@Entity
public class Centro {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String codigo;
    private String nombre;
    private String direccion;
    @OneToMany
    private List<Ubicacion> salas;

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

    public List<Ubicacion> getSalas() {
        return salas;
    }

    public void setSalas(List<Ubicacion> salas) {
        this.salas = salas;
    }

    @Override
    public String toString() {
        return "Centro{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", salas=" + salas +
                '}';
    }
}
