package com.vemec.api.controllers;

import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapperRepository;
import com.vemec.api.services.PacienteService;
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

    private PacienteService pacienteService;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            return new ResponseEntity<>(this.pacienteService.addNew(payload), null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            pacienteService = new PacienteService();
            return new ResponseEntity<>(this.pacienteService.getAll(),null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
                return new ResponseEntity<>(this.pacienteService.getByID(id), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(this.pacienteService.delete(id) ? "{'status':'SUCCESS'}" :"{'status':'BAD'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {
        try {
            return new ResponseEntity<>(this.pacienteService.update(id,payload), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
