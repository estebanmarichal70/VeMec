package com.vemec.api.models.patologias_wrapper;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatologiasWrapperRepository extends PagingAndSortingRepository<PatologiasWrapper, Integer> {
}
