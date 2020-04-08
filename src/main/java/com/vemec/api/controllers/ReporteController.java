package com.vemec.api.controllers;

import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.reporte.ReporteRepository;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/reporte")
public class ReporteController{
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, r);

            Ingreso i = ingresoRepository.findById(r.getIngreso().getId()).get();
            i.addToHistorial(r);
            reporteRepository.save(r);

            return new ResponseEntity<>(r, null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @GetMapping
    public @ResponseBody
    ResponseEntity getAll() {
        try {
            Iterable<Reporte> ubicaciones = reporteRepository.findAll();
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
            Optional<Reporte> r = reporteRepository.findById(id);
            if (r.isPresent()) {

                return new ResponseEntity<>(r.get(), null, HttpStatus.OK);
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
            reporteRepository.deleteById(id);
            return new ResponseEntity<>("{'status':'SUCCESS'}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            Optional<Reporte> re = reporteRepository.findById(id);
            Reporte r = new Reporte();
            r = Mappers.mapToReporte(payload, re.get());
            reporteRepository.save(r);
            return new ResponseEntity<>(r, null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
