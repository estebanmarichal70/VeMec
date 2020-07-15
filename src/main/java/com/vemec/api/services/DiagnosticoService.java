package com.vemec.api.services;

import com.vemec.api.models.diagnostico.Diagnostico;
import com.vemec.api.models.diagnostico.DiagnosticoRepository;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    public Diagnostico addNew(Map<String, Object> payload) throws Exception {
        try {

            Diagnostico d = new Diagnostico();
            d = Mappers.mapToDiagnostico(payload, d);
            Ingreso i = ingresoRepository.findById(d.getIngreso().getId()).get();
            i.addToDiagnosticos(d);
            diagnosticoRepository.save(d);
            return d;

        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> getAll(Integer idIngreso) throws Exception {
        try {

            Ingreso i = new Ingreso();
            i.setId(idIngreso);
            Iterable<Diagnostico> data = diagnosticoRepository.findAllByIngresoOrderByFechaAsc(i);
            Map<String, Object> result = new HashMap<>();
            result.put("diagnosticos", data);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
