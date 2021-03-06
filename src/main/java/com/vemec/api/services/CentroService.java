package com.vemec.api.services;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.centro.CentroRepository;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CentroService {
    @Autowired
    private CentroRepository centroRepository;

    public Centro addNew(Map<String, String> payload) throws Exception {
        try {
            Centro c = new Centro();
            c = Mappers.mapToCentro(payload, c);
            centroRepository.save(c);
            return c;
        } catch (Exception e) {
            throw e;
        }
    }

    public Iterable<Centro> getAll(Integer page, Integer limit, String nombre, String codigo) throws Exception {
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<Centro> pagedResult;
            if ((!nombre.equals("") && !nombre.equals("null")) && (!codigo.equals("") && !codigo.equals("null"))) {
                pagedResult = centroRepository.findAllByNombreContainingAndCodigoContaining(paging, nombre, codigo);
            } else if ((!nombre.equals("") && !nombre.equals("null")) && (codigo.equals("null") || codigo.equals(""))) {
                pagedResult = centroRepository.findAllByNombreContaining(paging, nombre);
            } else if ((nombre.equals("null") || nombre.equals("")) && (!codigo.equals("") && !codigo.equals("null"))) {
                pagedResult = centroRepository.findAllByCodigoContaining(paging, codigo);
            } else {
                pagedResult = centroRepository.findAll(paging);
            }

            List resultado = new LinkedList();
            resultado.add(pagedResult.getTotalPages());
            resultado.add(pagedResult.getTotalElements());
            resultado.add(pagedResult.toList());
            return resultado;
        } catch (Exception e) {
            throw e;
        }
    }

    public Centro getByID(Integer id) throws Exception {
        try {
            Optional<Centro> ce = centroRepository.findById(id);
            if (ce.isPresent())
                return ce.get();
            else {
                throw new Exception("Not Found");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Map<String, Object> getNombresAndId() throws Exception {
        try {
            Iterable<Centro> c = centroRepository.findAllOnlyNombreAndId();

            Map<String, Object> result = new HashMap<>();
            result.put("centros", c);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public Boolean delete(Integer id) throws Exception {
        try {
            Centro c = centroRepository.findById(id).get();
            List<Sala> lista = c.getSalas();
            lista.forEach(sala -> {
                List<Ingreso> ing = sala.getIngresos();
                ing.forEach(ingreso -> {
                    ingreso.getPaciente().removeFromIngresos(ingreso);
                });
            });
            centroRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public Centro update(Integer id, Map<String, String> payload) {

        try {
            Optional<Centro> ce = centroRepository.findById(id);
            Centro c = Mappers.mapToCentro(payload, ce.get());
            centroRepository.save(c);
            return c;
        } catch (Exception e) {
            throw e;
        }
    }
}
