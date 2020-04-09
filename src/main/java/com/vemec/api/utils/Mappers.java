package com.vemec.api.utils;
import com.vemec.api.constants.Estado;
import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.vemec.VeMec;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class Mappers {
    public static Centro mapToCentro(Map<String, String> payload, Centro c) {
        payload.forEach((key, value) -> {
            switch (key) {
                case "codigo": {
                    c.setCodigo(value);
                    break;
                }
                case "direccion": {
                    c.setDireccion(value);
                    break;
                }
                case "nombre": {
                    c.setNombre(value);
                    break;
                }
            }
        });
        return c;
    }

    public static Ubicacion mapToUbicacion(Map<String, Object> payload, Ubicacion u) {
        if(payload.get("nombre") != null){
            u.setNombre(payload.get("nombre").toString());
        }
        if(payload.get("capacidad") != null){
            u.setCapacidad((Integer) payload.get("capacidad"));
        }
        if(payload.get("centro") != null){
            Centro c = new Centro();
            c.setId((Integer) payload.get("centro"));
            u.setCentro(c);
        }
        return u;
    }

    public static Paciente mapToPaciente(Map<String, Object> payload, Paciente u) {
        if (payload.get("id") != null) {
            u.setId((Integer) (payload.get("id")));
        }

        if (payload.get("nombre") != null) {
            u.setNombre(payload.get("nombre").toString());
        }

        if (payload.get("apellido") != null) {
            u.setApellido(payload.get("apellido").toString());
        }

        if (payload.get("edad") != null) {
            u.setEdad((Integer) payload.get("edad"));
        }

        if (payload.get("patologias") != null) {
            PatologiasWrapper patologias = new PatologiasWrapper();
            patologias.setPatologias((List<String>) payload.get("patologias"));
            u.setPatologias(patologias);
        }
        return u;
    }

    public static Ingreso mapToIngreso(Map<String, Object> payload, Ingreso i) throws ParseException {
        if(payload.get("causa") != null ){
            i.setCausa(payload.get("causa").toString());
        }
        if(payload.get("estado") != null ){
            switch (payload.get("estado").toString()){
                case "ESTABLE":{
                    i.setEstado(Estado.ESTABLE);
                    break;
                }
                case "CRITICO":{
                    i.setEstado(Estado.CRITICO);
                    break;
                }
                case "INTERMEDIO":{
                    i.setEstado(Estado.INTERMEDIO);
                    break;
                }
                case "SANO":{
                    i.setEstado(Estado.SANO);
                    break;
                }
            }
        }
        if(payload.get("fechaEgreso") != null ){
            try{
                i.setFechaEgreso(Utils.parseToSqldate((String) payload.get("fechaEgreso")));
            }
            catch (Exception e){
                throw e;
            }
        }
        if(payload.get("fechaIngreso") != null ){
            try{
                i.setFechaIngreso(Utils.parseToSqldate((String) payload.get("fechaIngreso")));
            }
            catch (Exception e){
                throw e;
            }
        }
        if(payload.get("ubicacion") != null){
            Ubicacion u = new Ubicacion();
            u.setId((Integer)payload.get("ubicacion"));
            i.setUbicacion(u);
        }
        if(payload.get("vemec") != null){
            VeMec vm = new VeMec();
            vm.setId((Integer)payload.get("vemec"));
            i.setVemec(vm);
        }
         if(payload.get("paciente") != null){
             Paciente p = new Paciente();
             p.setId((Integer)payload.get("paciente"));
             i.setPaciente(p);
         }

        return i;
    }

    public static Reporte mapToReporte(Map<String, Object> payload, Reporte r) throws Exception {

        if (payload.get("presionMaxima") != null) {
            r.setPresionMaxima((Double) payload.get("presionMaxima"));
        }

        if (payload.get("presionMinima") != null) {
            r.setPresionMinima((Double) payload.get("presionMinima"));
        }

        if (payload.get("volGas") != null) {
            r.setVolGas((Double) payload.get("volGas"));
        }

        if (payload.get("frecGas") != null) {
            r.setFrecGas((Double) payload.get("frecGas"));
        }

        if(payload.get("mezcla") != null){
            r.setMezcla((Double) payload.get("mezcla"));
        }

        if(payload.get("humedadAire") != null){
            r.setHumedadAire((Double) payload.get("humedadAire"));
        }

        if(payload.get("tempEntrada") != null){
            r.setTempEntrada((Double) payload.get("tempEntrada"));
        }

        if(payload.get("tempSalida") != null){
            r.setTempSalida((Double) payload.get("tempSalida"));
        }

        if(payload.get("presionEntrada") != null){
            r.setPresionEntrada((Double) payload.get("presionEntrada"));
        }

        if(payload.get("presionSalida") != null){
            r.setPresionSalida((Double) payload.get("presionSalida"));
        }

        if(payload.get("time") != null){
            try{
                r.setTime(Utils.parseToSqldate((String) payload.get("time")));
            }
            catch (Exception e){
                throw e;
            }
        }

        if(payload.get("ingreso") != null){
            Ingreso i = new Ingreso();
            i.setId((Integer) payload.get("ingreso"));
            r.setIngreso(i);
        }

        if (payload.get("unidadPresion") != null) {
            r.setUnidadPresion((String) payload.get("unidadPresion"));
        }

        if (payload.get("unidadFrecuencia") != null) {
            r.setUnidadFrecuencia((String) payload.get("unidadFrecuencia"));
        }

        if (payload.get("unidadVolumen") != null) {
            r.setUnidadVolumen((String) payload.get("unidadVolumen"));
        }

        if (payload.get("unidadTemp") != null) {
            r.setUnidadTemp((String) payload.get("unidadTemp"));
        }

        if (payload.get("unidadHumedad") != null) {
            r.setUnidadHumedad((String) payload.get("unidadHumedad"));
        }
        return r;
    }

    public static VeMec mapToVeMec (Map < String, Object > payload, VeMec v){
        if(payload.get("marca") != null){
            v.setMarca(payload.get("marca").toString());
        }
        if(payload.get("modelo") != null){
            v.setModelo(payload.get("modelo").toString());
        }
        if(payload.get("estado") != null){
            v.setEstado((Boolean) payload.get("estado"));
        }
        if(payload.get("ubicacion") != null){
            Ubicacion u = new Ubicacion();
            u.setId((Integer) payload.get("ubicacion"));
            v.setUbicacion(u);
        }
        return v;
    }
}
