package com.vemec.api.models.sala;

        import com.vemec.api.models.centro.Centro;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.repository.PagingAndSortingRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends PagingAndSortingRepository<Sala, Integer> {

    Page<Sala> findAllByNombreContaining(Pageable pageable, String nombre);
    Page<Sala> findAllByNombreContainingAndCentro(Pageable pageable, String nombre, Centro centro);
    Page<Sala> findAllByCentro(Pageable pageable, Centro centro);
}
