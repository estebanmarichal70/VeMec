package com.vemec.api.models.paciente;

import com.vemec.api.constants.Sexo;
import com.vemec.api.models.centro.Centro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Integer> {

    Page<Paciente> findAllByNombreContainingAndApellidoContaining(Pageable pageable, String nombre, String apellido);
    Page<Paciente> findAllByNombreContaining(Pageable pageable, String nombre);
    Page<Paciente> findAllByApellidoContaining(Pageable pageable, String apellido);

    Long countAllBySexo(Sexo sexo);
    Long countAllByEdadBetween(Integer edad1, Integer edad2);
}