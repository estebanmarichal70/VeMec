package com.vemec.api.services;

import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapperRepository;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.utils.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PatologiasWrapperRepository patologiasWrapperRepository;

    public
    Paciente addNew(Map<String, Object> payload) {
        try {
            Paciente p = new Paciente();
            p = Mappers.mapToPaciente(payload, p);
            patologiasWrapperRepository.save(p.getPatologias());
            pacienteRepository.save(p);
            return p;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Iterable<Paciente> getAll(Integer page, Integer limit) {
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Paciente> pagedResult = pacienteRepository.findAll(paging);
            return pagedResult.toList();
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Paciente getByID(Integer id) throws Exception {
        try {
            Optional<Paciente> u = pacienteRepository.findById(id);
            if (u.isPresent()) {
                return u.get();
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Boolean delete(Integer id) {
        try {
            Paciente p = pacienteRepository.findById(id).get();
            PatologiasWrapper patologias = p.getPatologias();
            p.setPatologias(null);
            patologiasWrapperRepository.deleteById(patologias.getId());
            p.getIngresos().forEach((value)->{
                value.setVemec(null);
                value.setUbicacion(null);
            });

            pacienteRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            throw e;
        }
    }


    public
    Paciente update(Integer id,Map<String, Object> payload) {
        try {
            Optional<Paciente> pb = pacienteRepository.findById(id);
            Paciente p = new Paciente();
            p = Mappers.mapToPaciente(payload, pb.get());
            pacienteRepository.save(p);
            return p;
        } catch (Exception e) {
            throw e;
        }
    }
}
