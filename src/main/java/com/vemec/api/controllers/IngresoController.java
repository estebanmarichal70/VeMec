package com.vemec.api.controllers;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/v1/ingreso")
public class IngresoController {
    @Autowired
    private IngresoRepository ingresoRepository;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, i);

            //Paciente p = pacienteRepository.findById(p.getPaciente().getId()).get();
            ///p.addToUbicaciones(i);
            ingresoRepository.save(i);

            return new ResponseEntity<>(i, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<Ingreso> ingresos = ingresoRepository.findAll();
            return new ResponseEntity<>(ingresos,null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            Optional<Ingreso> i = ingresoRepository.findById(id);
            if (i.isPresent()) {

                return new ResponseEntity<>(i.get(), null, HttpStatus.OK);
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            ingresoRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            Optional<Ingreso> in = ingresoRepository.findById(id);
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, in.get());
            ingresoRepository.save(i);
            return new ResponseEntity<>(i, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
