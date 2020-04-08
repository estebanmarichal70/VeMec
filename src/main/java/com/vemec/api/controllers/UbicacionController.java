package com.vemec.api.controllers;


import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.centro.CentroRepository;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.ubicacion.UbicacionRepository;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/ubicacion")
public class UbicacionController{

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private CentroRepository centroRepository;


    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            Ubicacion u = new Ubicacion();
            u = Mappers.mapToUbicacion(payload, u);

            Centro c = centroRepository.findById(u.getCentro().getId()).get();
            c.addToUbicaciones(u);
            ubicacionRepository.save(u);

            return new ResponseEntity<>(u, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<Ubicacion> ubicaciones = ubicacionRepository.findAll();
            return new ResponseEntity<>(ubicaciones,null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            Optional<Ubicacion> u = ubicacionRepository.findById(id);
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
    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            Ubicacion u = ubicacionRepository.findById(id).get();
            u.getCentro().removeFromUbicaciones(u);
            ubicacionRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            Optional<Ubicacion> ub = ubicacionRepository.findById(id);
            Ubicacion u = new Ubicacion();
            u = Mappers.mapToUbicacion(payload, ub.get());
            ubicacionRepository.save(u);
            return new ResponseEntity<>(u, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}

