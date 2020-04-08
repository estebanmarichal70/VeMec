package com.vemec.api.controllers;

import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.ubicacion.UbicacionRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/vemec")
public class VeMecController {
    @Autowired
    private VeMecRepository veMecRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            VeMec v = new VeMec();
            v = Mappers.mapToVeMec(payload, v);

            Ubicacion u = ubicacionRepository.findById(v.getUbicacion().getId()).get();
            u.addToVeMecs(v);

            veMecRepository.save(v);
            return new ResponseEntity<>(v, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<VeMec> vemec = veMecRepository.findAll();
            return new ResponseEntity<>(vemec,null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            Optional<VeMec> v = veMecRepository.findById(id);
            if (v.isPresent()) {
                return new ResponseEntity<>(v.get(), null, HttpStatus.OK);
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
            VeMec v = veMecRepository.findById(id).get();
            v.getUbicacion().removeFromVemec(v);
            veMecRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            Optional<VeMec> ve = veMecRepository.findById(id);
            VeMec v =  Mappers.mapToVeMec(payload, ve.get());
            veMecRepository.save(v);
            return new ResponseEntity<>(v, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
