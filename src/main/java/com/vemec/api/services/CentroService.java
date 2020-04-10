package com.vemec.api.services;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.centro.CentroRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CentroService {
    @Autowired
    private CentroRepository centroRepository;

    public
    Centro addNew(Map<String, String> payload) throws Exception{
        try {
            Centro c = new Centro();
            c = Mappers.mapToCentro(payload, c);
            centroRepository.save(c);
            return c;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Iterable<Centro> getAll(Integer page, Integer limit) throws Exception{
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Centro> pagedResult = centroRepository.findAll(paging);
            return pagedResult.toList();
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Centro getByID(Integer id) throws Exception {
        try {
            Optional<Centro> ce = centroRepository.findById(id);
            if (ce.isPresent())
                return ce.get();
            else {
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
            centroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Centro update(Integer id, Map<String, String> payload) {

        try {
            Optional<Centro> ce = centroRepository.findById(id);
            Centro c = Mappers.mapToCentro(payload, ce.get());
            centroRepository.save(c);
            return c;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
