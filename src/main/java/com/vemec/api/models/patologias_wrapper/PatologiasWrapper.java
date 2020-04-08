package com.vemec.api.models.patologias_wrapper;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PatologiasWrapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    private List<String> patologias;

    public PatologiasWrapper() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getPatologias() {
        return patologias;
    }

    public void setPatologias(List<String> patologias) {
        this.patologias = patologias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatologiasWrapper that = (PatologiasWrapper) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patologias, that.patologias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patologias);
    }
}
