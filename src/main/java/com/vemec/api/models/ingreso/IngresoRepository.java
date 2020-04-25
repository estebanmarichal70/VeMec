package com.vemec.api.models.ingreso;

import com.vemec.api.constants.Estado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IngresoRepository extends PagingAndSortingRepository<Ingreso, Integer> {

    Page<Ingreso> findAllByCausaContaining(Pageable pageable, String causa);
    Page<Ingreso> findAllByCausaContainingAndId(Pageable pageable, String causa, Ingreso id);
    Page<Ingreso> findAllById(Pageable pageable, Ingreso id);
    Long countAllByFechaIngresoAfterAndFechaIngresoBeforeAndFechaEgreso(Date fechaInicio, Date fechaFin, Date FechaEgr);
    Long countAllByEstadoAndFechaEgreso(Estado estado, Date date);
    Long countAllByFechaEgreso(Date fecha);
}
