package com.vemec.api.services;

import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.reporte.ReporteRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

import java.util.Map;
import java.util.Optional;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    public
    Reporte addNew(Map<String, Object> payload) throws Exception {
        try {
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, r);

            Ingreso i = ingresoRepository.findById(r.getIngreso().getId()).get();
            i.addToHistorial(r);
            reporteRepository.save(r);

            return r;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Iterable<Reporte> getAll() throws Exception{
        try {
            return reporteRepository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Reporte getByID(Integer id) throws Exception {
        try {
            Optional<Reporte> r = reporteRepository.findById(id);
            if (r.isPresent()) {
                return r.get();
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Boolean delete(Integer id) throws Exception{
        try {
            Reporte r = reporteRepository.findById(id).get();
            r.getIngreso().removeFromReportes(r);
            reporteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Reporte update(Integer id, Map<String, Object> payload) throws Exception {

        try {
            Optional<Reporte> re = reporteRepository.findById(id);
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, re.get());
            reporteRepository.save(r);
            return r;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
