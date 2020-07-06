package com.vemec.api.models.ingreso;

import com.vemec.api.constants.Estado;
import com.vemec.api.models.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IngresoRepository extends PagingAndSortingRepository<Ingreso, Integer> {

    Page<Ingreso> findAllByIdContainingAndPaciente(Pageable pageable, Ingreso id, Paciente p);
    Page<Ingreso> findAllByPaciente(Pageable pageable, Paciente paciente);
    Long countAllByFechaIngresoAfterAndFechaIngresoBeforeAndFechaEgreso(Date fechaInicio, Date fechaFin, Date FechaEgr);
    Long countAllByEstadoAndFechaEgreso(Estado estado, Date date);
    Long countAllByFechaEgreso(Date fecha);

    Ingreso findByPacienteAndFechaEgreso(Paciente paciente, Date fecha);
}
