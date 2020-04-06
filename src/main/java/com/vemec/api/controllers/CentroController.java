package com.vemec.api.controllers;

import com.vemec.api.models.centro.*;
import com.vemec.api.utils.ErrorHandler;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vemec.api.utils.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/centro")
public class CentroController {

    @Autowired
    private CentroRepository centroRepository;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, String> payload) {
        try {
            Centro c = new Centro();
            this.mapToCentro(payload, c);
            centroRepository.save(c);
            return new ResponseEntity<>(c, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }

    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<Centro> ce = centroRepository.findAll();
            return new ResponseEntity<>(ce,null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            Optional<Centro> ce = centroRepository.findById(id);
            if (ce.isPresent())
                return new ResponseEntity<>(ce,null, HttpStatus.OK);
            else {
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
            centroRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }

    }

    private void mapToCentro(Map<String, String> payload, Centro c){

        payload.forEach((key, value) -> {
            switch (key) {
                case "codigo": {
                    c.setCodigo(value);
                    break;
                }
                case "direccion":{
                    c.setDireccion(value);
                    break;
                }
                case "nombre":{
                    c.setNombre(value);
                    break;
                }
            }
        });
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity updateServer(@PathVariable("id") Integer id, @RequestBody Map<String, String> payload) {

        try {
            Optional<Centro> ce = centroRepository.findById(id);
            Centro c = ce.get();
            this.mapToCentro(payload, c);
            centroRepository.save(c);
            return new ResponseEntity<>(c, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
