package com.vemec.api.controllers;

import com.vemec.api.models.reporte.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/v1/reporte")
public class ReporteController{
    @Autowired
    private ReporteRepository reporteRepository;
}
