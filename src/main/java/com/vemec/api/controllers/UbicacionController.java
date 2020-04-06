package com.vemec.api.controllers;

import com.vemec.api.models.ubicacion.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/v1/ubicacion")
public class UbicacionController{

    @Autowired
    private UbicacionRepository ubicacionRepository;
}
