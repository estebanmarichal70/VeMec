package com.vemec.api.controllers;

import com.vemec.api.models.ingreso.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/ingreso")
public class IngresoController {
    @Autowired
    private IngresoRepository ingresoRepository;
}
