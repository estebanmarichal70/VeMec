package com.vemec.api.utils;
import com.vemec.api.constants.Estado;
import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.vemec.VeMec;
import java.util.List;
import java.sql.Date;
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
        if(payload.get("nombre") != null){
            u.setCapacidad((Integer) payload.get("capacidad"));
        }
        if(payload.get("nombre") != null){
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

    public static Ingreso mapToIngreso(Map<String, Object> payload, Ingreso i){
        if(payload.get("causa") != null ){
            i.setCausa(payload.get("causa").toString());
        }
        if(payload.get("estado") != null ){
            i.setEstado((Estado) payload.get("estado"));
        }
        if(payload.get("fechaEgreso") != null ){
            i.setFechaEgreso((Date)payload.get("fechaEgreso"));
        }
        if(payload.get("fechaIngreso") != null ){
            i.setFechaEgreso((Date)payload.get("fechaIngreso"));
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

    public static Reporte mapToReporte(Map<String, Object> payload, Reporte r) {

        if (payload.get("presionMaxima") != null) {
            r.setPresionMaxima((Float) payload.get("presionMaxima"));
        }

        if (payload.get("presionMinima") != null) {
            r.setPresionMinima((Float) payload.get("presionMinima"));
        }

        if (payload.get("volGas") != null) {
            r.setVolGas((Float) payload.get("volGas"));
        }

        if (payload.get("frecGas") != null) {
            r.setFrecGas((Float) payload.get("frecGas"));
        }

        if(payload.get("mezcla") != null){
            r.setMezcla((Float) payload.get("mezcla"));
        }

        if(payload.get("humedadAire") != null){
            r.setHumedadAire((Float) payload.get("humedadAire"));
        }

        if(payload.get("tempEntrada") != null){
            r.setTempEntrada((Float) payload.get("tempEntrada"));
        }

        if(payload.get("tempSalida") != null){
            r.setTempSalida((Float) payload.get("tempSalida"));
        }

        if(payload.get("presionEntrada") != null){
            r.setTempSalida((Float) payload.get("presionEntrada"));
        }

        if(payload.get("presionSalida") != null){
            r.setPresionSalida((Float) payload.get("presionSalida"));
        }

        if(payload.get("time") != null){
            r.setTime((Date) payload.get("time"));
        }

        if(payload.get("ingreso") != null){
            Ingreso i = new Ingreso();
            i.setId((Integer) payload.get("ingreso"));
            r.setIngreso(i);
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
