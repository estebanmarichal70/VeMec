package com.vemec.api.services;

import com.vemec.api.constants.Sexo;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapperRepository;
import com.vemec.api.utils.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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


    public Map<String, Long> countAllBySexo() {
        try {
            Map<String, Long> resultado = new HashMap<>();

            Long counter = pacienteRepository.countAllBySexo(Sexo.FEMENINO);
            resultado.put("cant_femenino", counter);


            counter = pacienteRepository.countAllBySexo(Sexo.MASCULINO);
            resultado.put("cant_masculino", counter);

            return resultado;

        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Iterable<Paciente> getAll(Integer page, Integer limit, String nombre, String apellido) {
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Paciente> pagedResult;
            if((!nombre.equals("") && !nombre.equals("null")) && (!apellido.equals("") && !apellido.equals("null"))) {
                pagedResult = pacienteRepository.findAllByNombreContainingAndApellidoContaining(paging,nombre,apellido);
            }else if((!nombre.equals("") && !nombre.equals("null")) && (apellido.equals("") || apellido.equals("null"))) {
                pagedResult = pacienteRepository.findAllByNombreContaining(paging,nombre);
            }else if((!apellido.equals("") && !apellido.equals("null")) && ((nombre.equals("") || apellido.equals("null")))) {
                pagedResult = pacienteRepository.findAllByApellidoContaining(paging,apellido);
            }else {
                pagedResult = pacienteRepository.findAll(paging);
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
            if(patologias != null){
                p.setPatologias(null);
                patologiasWrapperRepository.deleteById(patologias.getId());
            }
            p.getIngresos().forEach((value)->{
                value.getSala().removefromIngresos(value);
                value.setPaciente(null);
                value.getVemec().setEstado(false);
                value.setVemec(null);
                value.setSala(null);
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

    public Map<String, Long> countAllByEdadBetween() {
        try {
            Map<String, Long> resultado = new HashMap<>();

            Long counter = pacienteRepository.countAllByEdadBetween(0,9);
            resultado.put("cero_a_nueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(10,19);
            resultado.put("diez_a_diecinueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(20,29);
            resultado.put("veinte_a_veintinueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(30,39);
            resultado.put("treinta_a_treintainueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(40,49);
            resultado.put("cuarenta_a_cuarentainueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(50,59);
            resultado.put("cincuenta_a_cincuentainueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(60,69);
            resultado.put("sesenta_a_sesentainueve", counter);


            counter = pacienteRepository.countAllByEdadBetween(70,999);
            resultado.put("mas_de_setenta", counter);
            return resultado;

        }
        catch (Exception e) {
            throw e;
        }
    }
}
