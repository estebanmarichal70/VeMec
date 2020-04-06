package com.vemec.api.controllers;

import com.vemec.api.models.centro.CentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/v1/centro")
public class CentroController {

    @Autowired
    private CentroRepository centroRepository;
}
