package com.vemec.api.services;

import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.ubicacion.UbicacionRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
@Service
public class IngresoService {
    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private VeMecRepository vemecRepository;

    public
    Ingreso addNew(Map<String, Object> payload) throws Exception {
        try {
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, i);
            Paciente p = pacienteRepository.findById(i.getPaciente().getId()).get();
            p.addToIngresos(i);
            Ubicacion u = ubicacionRepository.findById(i.getUbicacion().getId()).get();
            i.setUbicacion(u);
            VeMec v = vemecRepository.findById(i.getVemec().getId()).get();
            v.setEstado(true);
            i.setVemec(v);
            ingresoRepository.save(i);

            return i;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Iterable<Ingreso> getAll() throws Exception{
        try {
            return ingresoRepository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Ingreso getByID (Integer id) throws Exception {
        try {
            Optional<Ingreso> i = ingresoRepository.findById(id);
            if (i.isPresent()) {
                return i.get();
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Boolean delete (Integer id) throws Exception{
        try {
            Ingreso i = ingresoRepository.findById(id).get();
            i.getVemec().setEstado(false);
            i.setUbicacion(null);
            i.setVemec(null);
            i.getPaciente().removeFromIngresos(i);
            ingresoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Ingreso update(Integer id, Map<String, Object> payload) throws Exception{

        try {
            Optional<Ingreso> in = ingresoRepository.findById(id);
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, in.get());
            ingresoRepository.save(i);
            return i;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
