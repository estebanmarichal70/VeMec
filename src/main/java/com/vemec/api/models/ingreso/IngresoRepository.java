package com.vemec.api.models.ingreso;

import com.vemec.api.models.sala.Sala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoRepository extends PagingAndSortingRepository<Ingreso, Integer> {

    Page<Ingreso> findAllByCausaContaining(Pageable pageable, String causa);
}
