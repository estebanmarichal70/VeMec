package com.vemec.api.services;

import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.sala.SalaRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IngresoService {
    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private VeMecRepository vemecRepository;

    public
    Ingreso addNew(Map<String, Object> payload) throws Exception {
        try {
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, i);
            Paciente p = pacienteRepository.findById(i.getPaciente().getId()).get();
            p.addToIngresos(i);
            Sala u = salaRepository.findById(i.getSala().getId()).get();
            u.addToIngresos(i);
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
    Iterable<Ingreso> getAll(Integer page, Integer limit, String causa) throws Exception{
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Ingreso> pagedResult;
            System.out.println(causa);
            if(!causa.equals("") && !causa.equals("null")) {
                System.out.println("busco por causa");
                pagedResult = ingresoRepository.findAllByCausaContaining(paging,causa);
            }
            else {
                System.out.println("busco por todo");
                pagedResult = ingresoRepository.findAll(paging);
            }
            List resultado = new LinkedList();
            resultado.add(pagedResult.getTotalPages());
            resultado.add(pagedResult.getTotalElements());
            resultado.add(pagedResult.toList());
            return resultado;

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
            i.getSala().removefromIngresos(i);
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
