package com.vemec.api.controllers;

import com.vemec.api.services.VeMecService;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/vemec")
public class VeMecController {
    @Autowired
    private VeMecService veMecService;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNew(@RequestBody Map<String, Object> payload) {
        try {
            return new ResponseEntity<>(this.veMecService.addNew(payload), null, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity getAll(@RequestParam Integer page,@RequestParam Integer limit) {
        try {
            return new ResponseEntity<>(this.veMecService.getAll(page-1,limit),null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getByID(@PathVariable("id") Integer id)   {
        try {
                return new ResponseEntity<>(this.veMecService.getByID(id), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<>(this.veMecService.delete(id) ? "{\"status\":\"SUCCESS\"}":"{\"status\":\"BAD\"}",null, HttpStatus.OK);
        } catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> payload) {

        try {
            return new ResponseEntity<>(this.veMecService.update(id,payload), null, HttpStatus.OK);
        }
        catch (Exception e) {
            return Utils.mapErrors(e);
        }
    }
}
