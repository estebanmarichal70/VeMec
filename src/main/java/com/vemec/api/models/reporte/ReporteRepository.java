package com.vemec.api.models.reporte;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends PagingAndSortingRepository<Reporte, Integer> {

}
