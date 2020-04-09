package com.vemec.api.services;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.centro.CentroRepository;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.ubicacion.UbicacionRepository;
import com.vemec.api.utils.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Autowired
    private CentroRepository centroRepository;

    public
    Ubicacion addNew(Map<String, Object> payload) throws Exception{
        try {
            Ubicacion u = new Ubicacion();
            u = Mappers.mapToUbicacion(payload, u);

            Centro c = centroRepository.findById(u.getCentro().getId()).get();
            c.addToUbicaciones(u);
            ubicacionRepository.save(u);

            return u;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Iterable<Ubicacion> getAll() throws Exception{
        try {

            return ubicacionRepository.findAll();
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Ubicacion getByID(Integer id) throws Exception {
        try {
            Optional<Ubicacion> u = ubicacionRepository.findById(id);
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
            Ubicacion u = ubicacionRepository.findById(id).get();
            u.getCentro().removeFromUbicaciones(u);
            ubicacionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Ubicacion update(Integer id, Map<String, Object> payload) throws Exception{

        try {
            Optional<Ubicacion> ub = ubicacionRepository.findById(id);
            Ubicacion u = new Ubicacion();
            u = Mappers.mapToUbicacion(payload, ub.get());
            ubicacionRepository.save(u);
            return u;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
