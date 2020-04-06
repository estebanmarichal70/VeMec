package com.vemec.api.controllers;

import com.vemec.api.models.vemec.VeMecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/v1/vemec")
public class VeMecController {
    @Autowired
    private VeMecRepository veMecRepository;
}
