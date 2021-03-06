package com.vemec.api.controllers;

import com.vemec.api.services.IngresoService;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(path = "/api/v1/ingreso")
public class IngresoController {
    @Autowired
    private IngresoService ingresoService;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            return new ResponseEntity<>(this.ingresoService.addNew(payload), null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAll(@RequestParam Integer page,@RequestParam Integer limit, @RequestParam Integer id, @RequestParam Integer idP) {
        try {
            return new ResponseEntity<>(this.ingresoService.getAll(page -1, limit, id, idP),null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
            return new ResponseEntity<>(this.ingresoService.getByID(id), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(this.ingresoService.delete(id) ? "{\"status\":\"SUCCESS\"}":"{\"status\":\"BAD\"}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            return new ResponseEntity<>(this.ingresoService.update(id, payload), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @PutMapping(path = "/finalizar/{id}")
    public @ResponseBody
    ResponseEntity finalizarIngreso(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            return new ResponseEntity<>(this.ingresoService.finalizarIngreso(id, payload), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/sala/{id}")
    public @ResponseBody
    ResponseEntity vemecSala(@PathVariable("id") Integer id){
        try{
            return new ResponseEntity<>(this.ingresoService.vemecSala(id), null, HttpStatus.OK);
        }
        catch(Exception e){
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/hoy")
    public @ResponseBody
    ResponseEntity ingresosHoy(){
        try{
            return new ResponseEntity<>(this.ingresoService.countAllByFechaIngresoAfterAndFechaIngresoBeforeAndFechaEgreso(), null, HttpStatus.OK);
        }
        catch(Exception e){
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/total")
    public @ResponseBody
    ResponseEntity ingresosTotal(){
        try{
            return new ResponseEntity<>(this.ingresoService.countAllByFechaEgreso(), null, HttpStatus.OK);
        }
        catch(Exception e){
            return Utils.mapErrors(e);
        }
    }

    @GetMapping("/cnt_by_estado")
    public @ResponseBody
    ResponseEntity countAllByEstado() {
        try {
            return new ResponseEntity<>(ingresoService.countAllByEstado(),null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
