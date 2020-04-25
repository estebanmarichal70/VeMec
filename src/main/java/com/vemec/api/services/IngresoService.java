package com.vemec.api.services;

import com.vemec.api.constants.Estado;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.ingreso.IngresoRepository;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.paciente.PacienteRepository;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.sala.SalaRepository;
import com.vemec.api.models.vemec.VeMec;
import com.vemec.api.models.vemec.VeMecRepository;
import com.vemec.api.utils.Mappers;
import com.vemec.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IngresoService {
    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private VeMecRepository vemecRepository;

    public
    Ingreso addNew(Map<String, Object> payload) throws Exception {
        try {
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, i);
            Paciente p = pacienteRepository.findById(i.getPaciente().getId()).get();
            p.addToIngresos(i);
            Sala u = salaRepository.findById(i.getSala().getId()).get();
            u.addToIngresos(i);
            VeMec v = vemecRepository.findById(i.getVemec().getId()).get();
            v.setEstado(true);
            i.setVemec(v);
            ingresoRepository.save(i);

            return i;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public
    Iterable<Ingreso> getAll(Integer page, Integer limit, String causa, Integer id) throws Exception{
        try {
            Pageable paging = PageRequest.of(page, limit,  Sort.by("id").descending());
            Page<Ingreso> pagedResult;
            Integer ingresoID;

            if ((!causa.equals("") && !causa.equals("null")) && !id.equals("") && !id.equals("null") ) {
                ingresoID = id;
                Ingreso i = new Ingreso();
                i.setId(ingresoID);
                pagedResult = ingresoRepository.findAllByCausaContainingAndId(paging, causa, i);

            } else if ((causa.equals("") || causa.equals("null")) && (!id.equals("") && !id.equals("null")) ) {
                ingresoID = id;
                Ingreso i = new Ingreso();
                i.setId(ingresoID);
                pagedResult = ingresoRepository.findAllById(paging, i);


            }else if((!causa.equals("") && !causa.equals("null")) && (id.equals("") || id.equals("null"))){
                pagedResult = ingresoRepository.findAllByCausaContaining(paging, causa);

            }else{
                pagedResult = ingresoRepository.findAll(paging);

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
    public
    Ingreso getByID (Integer id) throws Exception {
        try {
            Optional<Ingreso> i = ingresoRepository.findById(id);
            if (i.isPresent()) {
                return i.get();
            }else {
                throw new Exception("Not Found");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Boolean delete (Integer id) throws Exception{
        try {
            Ingreso i = ingresoRepository.findById(id).get();
            i.getVemec().setEstado(false);
            i.getSala().removefromIngresos(i);
            i.setVemec(null);
            i.getPaciente().removeFromIngresos(i);
            ingresoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    public
    Ingreso update(Integer id, Map<String, Object> payload) throws Exception{
        try {
            Optional<Ingreso> in = ingresoRepository.findById(id);
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, in.get());
            ingresoRepository.save(i);
            return i;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public
    Ingreso finalizarIngreso(Integer id, Map<String, Object> payload) throws Exception{
        try {
            Optional<Ingreso> in = ingresoRepository.findById(id);
            in.get().getVemec().setEstado(false);
            Ingreso i = new Ingreso();
            i = Mappers.mapToIngreso(payload, in.get());
            ingresoRepository.save(i);
            return i;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Sala vemecSala(Integer id) throws Exception{
        try{
            Sala sala = ingresoRepository.findById(id).get().getSala();
            return sala;
        }
        catch (Exception e){
            throw e;
        }
    }
    public
    List pacienteSalaVemec(Integer id) throws Exception{
        try{
            Ingreso i = ingresoRepository.findById(id).get();
            Paciente paciente = i.getPaciente();
            Sala sala = i.getSala();
            VeMec vemec = i.getVemec();
            List resultado = new LinkedList();
            resultado.add(paciente);
            resultado.add(sala);
            resultado.add(vemec);
            return resultado;
        }
        catch (Exception e){
            throw e;
        }
    }
    public
    long countAllByFechaIngresoAfterAndFechaIngresoBeforeAndFechaEgreso() throws Exception {
        try{
            Calendar c = Calendar.getInstance();
            c.set(Calendar.HOUR_OF_DAY, 00);
            c.set(Calendar.MINUTE, 00);
            c.set(Calendar.SECOND, 00);
            Date date1 = c.getTime();
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            Date date2 = c.getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecha = dateFormat.format(date1);
            String fecha2 = dateFormat.format(date2);
            date1 = Utils.parseToSqldate(fecha);
            date2 = Utils.parseToSqldate(fecha2);
            long count = ingresoRepository.countAllByFechaIngresoAfterAndFechaIngresoBeforeAndFechaEgreso(date1, date2, null);
            return count;
        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Map<String, Long> countAllByEstado() {
        try {
            Map<String, Long> resultado = new HashMap<>();

            Long counter = ingresoRepository.countAllByEstadoAndFechaEgreso(Estado.CRITICO, null);
            resultado.put("cant_critico", counter);


            counter = ingresoRepository.countAllByEstadoAndFechaEgreso(Estado.DIFUNTO, null);
            resultado.put("cant_difunto", counter);


            counter = ingresoRepository.countAllByEstadoAndFechaEgreso(Estado.ESTABLE, null);
            resultado.put("cant_estable", counter);


            counter = ingresoRepository.countAllByEstadoAndFechaEgreso(Estado.INTERMEDIO, null);
            resultado.put("cant_intermedio", counter);


            counter = ingresoRepository.countAllByEstadoAndFechaEgreso(Estado.SANO, null);
            resultado.put("cant_sano", counter);
            return resultado;

        }
        catch (Exception e) {
            throw e;
        }
    }
    public
    Long countAllByFechaEgreso() throws Exception {
        try {
            long count = ingresoRepository.countAllByFechaEgreso(null);
            return count;
        }
        catch (Exception e) {
            throw e;
        }
    }
}
