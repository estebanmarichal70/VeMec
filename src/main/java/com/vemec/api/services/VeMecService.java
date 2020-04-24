package com.vemec.api.services;

import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.sala.SalaRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class VeMecService {
    @Autowired
    private VeMecRepository veMecRepository;

    @Autowired
    private SalaRepository salaRepository;

    public
    VeMec addNew(Map<String, Object> payload) throws Exception{
        try {
            VeMec v = new VeMec();
            v = Mappers.mapToVeMec(payload, v);

            Sala u = salaRepository.findById(v.getSala().getId()).get();
            u.addToVeMecs(v);

            veMecRepository.save(v);
            return v;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Iterable<VeMec> getAll(Integer page, Integer limit) throws Exception{
        try {
            Pageable paging = PageRequest.of(page, limit);
            Page<VeMec> pagedResult = veMecRepository.findAll(paging);
            return pagedResult.toList();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    VeMec getByID(Integer id) throws Exception {
        try {
            Optional<VeMec> v = veMecRepository.findById(id);
            if (v.isPresent()) {
                return v.get();
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
            VeMec v = veMecRepository.findById(id).get();
            Sala sala = v.getSala();
            sala.removeFromVemec(v);
            sala.removeVeMecfromIngreso(v);
            veMecRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    VeMec update(Integer id, Map<String, Object> payload) throws Exception{

        try {
            Optional<VeMec> ve = veMecRepository.findById(id);
            VeMec v =  Mappers.mapToVeMec(payload, ve.get());
            veMecRepository.save(v);
            return v;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public Map<String, Long> countAllByEstado() {
        try {
            Map<String, Long> resultado = new HashMap<>();

            Long counter = veMecRepository.countAllByEstado(true);
            resultado.put("usados", counter);


            counter = veMecRepository.countAllByEstado(false);
            resultado.put("libres", counter);


            return resultado;

        }
        catch (Exception e) {
            throw e;
        }
    }
}
