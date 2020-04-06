package com.vemec.api.models.centro;

import com.vemec.api.models.ubicacion.Ubicacion;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
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

    @Override
    public String toString() {
        return "Centro{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ubicaciones=" + ubicaciones +
                '}';
    }
}
