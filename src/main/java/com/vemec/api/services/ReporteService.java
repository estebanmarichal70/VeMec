package com.vemec.api.services;

import com.vemec.api.constants.Alerta;
import com.vemec.api.controllers.WebSocketController;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.reporte.ReporteRepository;
import com.vemec.api.utils.Mappers;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @Transactional
    public Reporte addNew(Map<String, Object> payload) throws Exception {
        try {
            Reporte r = new Reporte();
            JSONObject body = new JSONObject((String)payload.get("reporte"));
            r = Mappers.mapToReporte(body, r);

            Paciente p = new Paciente();
            p.setId((Integer) payload.get("cedula"));
            Ingreso i = ingresoRepository.findByPacienteAndFechaEgreso(p, null);
            i.addToHistorial(r);
            reporteRepository.save(r);

            if (r.getAlerta() == Alerta.BATERIA || r.getAlerta() == Alerta.PULSACIONES || r.getAlerta() == Alerta.PULSACIONES_BATERIA) {
                Map<String, Object> data = new HashMap<>();
                data.put("paciente", r.getIngreso().getPaciente());
                data.put("reporte", r);
                data.put("sala", r.getIngreso().getSala());
                WebSocketController.sendWebSocketUpdate(data);
            }

            return r;
        } catch (Exception e) {
            throw e;
        }
    }

    public Iterable<Reporte> getAll(Integer page, Integer limit, String ingresoId) throws Exception {
        try {

            Pageable paging = PageRequest.of(page, limit, Sort.by("id").descending());
            Page<Reporte> pagedResult;

            if (!ingresoId.equals("") && !ingresoId.equals("null")) {
                Ingreso i = new Ingreso();
                Integer ingId = Integer.parseInt(ingresoId);
                i.setId(ingId);
                pagedResult = reporteRepository.findAllByIngreso(paging, i);

            } else {
                pagedResult = reporteRepository.findAll(paging);
            }

            List resultado = new LinkedList();
            resultado.add(pagedResult.getTotalPages());
            resultado.add(pagedResult.getTotalElements());
            resultado.add(pagedResult.toList());
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    public Reporte getByID(Integer id) throws Exception {
        try {
            Optional<Reporte> r = reporteRepository.findById(id);
            if (r.isPresent()) {
                return r.get();
            } else {
                throw new Exception("Not Found");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        try {
            Reporte r = reporteRepository.findById(id).get();
            r.getIngreso().removeFromReportes(r);
            reporteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Reporte update(Integer id, Map<String, Object> payload) throws Exception {

        try {
            Optional<Reporte> re = reporteRepository.findById(id);
            Reporte r = new Reporte();
            //r = Mappers.mapToReporte(payload, re.get());
            reporteRepository.save(r);
            return r;
        } catch (Exception e) {
            throw e;
        }
    }
}
