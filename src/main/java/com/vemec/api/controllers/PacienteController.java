package com.vemec.api.controllers;

import com.vemec.api.models.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/paciente")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
}
