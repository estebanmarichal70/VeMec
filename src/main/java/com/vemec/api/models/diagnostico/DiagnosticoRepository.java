package com.vemec.api.models.diagnostico;

import com.vemec.api.models.ingreso.Ingreso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticoRepository extends PagingAndSortingRepository<Diagnostico, Integer> {
    Iterable<Diagnostico> findAllByIngresoOrderByFechaAsc(Ingreso ingreso);
}
