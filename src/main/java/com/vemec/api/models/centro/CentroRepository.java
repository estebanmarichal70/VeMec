package com.vemec.api.models.centro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public interface CentroRepository extends PagingAndSortingRepository<Centro, Integer> {

    //Page<Centro> findAllByNombreOrCodigoContaining(Pageable pageable, String nombre, String codigo);
    Page<Centro> findAllByNombreContainingAndCodigoContaining(Pageable pageable, String nombre, String codigo);
    Page<Centro> findAllByNombreContaining(Pageable pageable, String nombre);
    Page<Centro> findAllByCodigoContaining(Pageable pageable, String codigo);

    @Query("select distinct c.id, c.nombre from Centro c")
    Iterable<Centro> findAllOnlyNombreAndId();
}
