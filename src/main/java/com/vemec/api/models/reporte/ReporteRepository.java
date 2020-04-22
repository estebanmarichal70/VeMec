package com.vemec.api.models.reporte;

import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.sala.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends PagingAndSortingRepository<Reporte, Integer> {

    Page<Reporte> findAllByIngreso(Pageable pageable, Ingreso id);
}
