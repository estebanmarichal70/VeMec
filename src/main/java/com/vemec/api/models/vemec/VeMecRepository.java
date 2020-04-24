package com.vemec.api.models.vemec;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeMecRepository extends PagingAndSortingRepository<VeMec, Integer> {
    Long countAllByEstado(Boolean estado);
}
