package com.vemec.api.services;

import com.vemec.api.models.diagnostico.Diagnostico;
import com.vemec.api.models.diagnostico.DiagnosticoRepository;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
