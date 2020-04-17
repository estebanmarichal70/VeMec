package com.vemec.api.services;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.centro.CentroRepository;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.sala.SalaRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CentroRepository centroRepository;

    public Sala addNew(Map<String, Object> payload) throws Exception{
        try {
            Sala u = new Sala();
            u = Mappers.mapToSala(payload, u);

            Centro c = centroRepository.findById(u.getCentro().getId()).get();
            c.addToUbicaciones(u);
            salaRepository.save(u);

            return u;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Iterable<Sala> getAll(Integer page, Integer limit, String nombre) throws Exception{
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Sala> pagedResult;
            if(!nombre.equals("") && !nombre.equals("null")) {
                pagedResult = salaRepository.findAllByNombreContaining(paging,nombre);
            }else {
                pagedResult = salaRepository.findAll(paging);
            }
            List resultado = new LinkedList();
            resultado.add(pagedResult.getTotalPages());
            resultado.add(pagedResult.getTotalElements());
            resultado.add(pagedResult.toList());
            return resultado;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public Sala getByID(Integer id) throws Exception {
        try {
            Optional<Sala> u = salaRepository.findById(id);
            if (u.isPresent()) {
                return u.get();
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Boolean delete(Integer id) throws Exception{
        try {
            Sala u = salaRepository.findById(id).get();
            u.getCentro().removeFromSalas(u);
            salaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public Sala update(Integer id, Map<String, Object> payload) throws Exception{

        try {
            Optional<Sala> ub = salaRepository.findById(id);
            Sala u = new Sala();
            u = Mappers.mapToSala(payload, ub.get());
            salaRepository.save(u);
            return u;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
