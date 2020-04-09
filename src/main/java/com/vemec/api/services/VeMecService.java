package com.vemec.api.services;

import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.ubicacion.UbicacionRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class VeMecService {
    @Autowired
    private VeMecRepository veMecRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public
    VeMec addNew(Map<String, Object> payload) throws Exception{
        try {
            VeMec v = new VeMec();
            v = Mappers.mapToVeMec(payload, v);

            Ubicacion u = ubicacionRepository.findById(v.getUbicacion().getId()).get();
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
            v.getUbicacion().removeFromVemec(v);
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
}
