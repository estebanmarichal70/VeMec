package com.vemec.api.models.diagnostico;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vemec.api.models.ingreso.Ingreso;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Component
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @JsonBackReference
    @ManyToOne
    private Ingreso ingreso;

    private String medico;
    private String asistente;
    private String medicacion;
    private String riesgo;
    private String motivos;

    public Diagnostico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getAsistente() {
        return asistente;
    }

    public void setAsistente(String asistente) {
        this.asistente = asistente;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getMotivos() {
        return motivos;
    }

    public void setMotivos(String motivos) {
        this.motivos = motivos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnostico that = (Diagnostico) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(ingreso, that.ingreso) &&
                Objects.equals(medico, that.medico) &&
                Objects.equals(asistente, that.asistente) &&
                Objects.equals(medicacion, that.medicacion) &&
                Objects.equals(riesgo, that.riesgo) &&
                Objects.equals(motivos, that.motivos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, ingreso, medico, asistente, medicacion, riesgo, motivos);
    }
}

/*
* Fecha y hora de ingreso al proceso tratamiento de la patología.
Médico tratante que dispone el ingreso: ….
Motivos: ej: tras detectar síntomas se practicaron exámenes que confirmaron afección, se dispuso
internación dados sus antecedentes de …
Nivel de riesgo: Medio. (En todos los pasos siempre debe registrarse médico tratante)

* */