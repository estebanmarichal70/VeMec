package com.vemec.api.controllers;

import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapperRepository;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PatologiasWrapperRepository patologiasWrapperRepository;
    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            Paciente p = new Paciente();
            p = Mappers.mapToPaciente(payload, p);
            patologiasWrapperRepository.save(p.getPatologias());
            pacienteRepository.save(p);
            return new ResponseEntity<>(p, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<Paciente> paciente = pacienteRepository.findAll();
            return new ResponseEntity<>(paciente,null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            Optional<Paciente> u = pacienteRepository.findById(id);
            if (u.isPresent()) {
                return new ResponseEntity<>(u.get(), null, HttpStatus.OK);
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    /*@DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            ubicacionRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }*/

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {
        try {

            Optional<Paciente> pb = pacienteRepository.findById(id);
            Paciente p = new Paciente();
            p = Mappers.mapToPaciente(payload, pb.get());
            pacienteRepository.save(p);
            return new ResponseEntity<>(pb, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
