package com.vemec.api.utils;

import com.vemec.api.constants.Estado;
import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.ubicacion.Ubicacion;
import com.vemec.api.models.vemec.VeMec;

import java.sql.Date;
import java.util.Map;

public class Mappers {

    public static Centro mapToCentro(Map<String, String> payload, Centro c){

        payload.forEach((key, value) -> {
            switch (key) {
                case "codigo": {
                    c.setCodigo(value);
                    break;
                }
                case "direccion":{
                    c.setDireccion(value);
                    break;
                }
                case "nombre":{
                    c.setNombre(value);
                    break;
                }
            }
        });
        return c;
    }
    public static Ubicacion mapToUbicacion(Map<String, Object> payload, Ubicacion u){
        u.setNombre(payload.get("nombre").toString());
        u.setCapacidad((Integer)payload.get("capacidad"));
        Centro c = new Centro();
        c.setId((Integer) payload.get("centro"));
        u.setCentro(c);
        return u;
    }
    public static Ingreso mapToIngreso(Map<String, Object> payload, Ingreso i){
         i.setCausa(payload.get("causa").toString());
         i.setEstado((Estado) payload.get("estado"));
         i.setFechaEgreso((Date)payload.get("fechaEgreso"));
         i.setFechaIngreso((Date)payload.get("fechaIngreso"));
         Ubicacion u = new Ubicacion();
         u.setId((Integer)payload.get("ubicacion"));
         i.setUbicacion(u);
         VeMec vm = new VeMec();
         vm.setId((Integer)payload.get("vemec"));
         i.setVemec(vm);
         Paciente p = new Paciente();
         p.setId((Integer)payload.get("paciente"));
         i.setPaciente(p);
        return i;
    }
    public static Reporte mapToReporte(Map<String, Object> payload, Reporte r){
        r.setPresionMaxima((Float)payload.get("presionMaxima"));
        r.setPresionMinima((Float)payload.get("presionMinima"));
        r.setVolGas((Float)payload.get("volGas"));
        r.setFrecGas((Float)payload.get("frecGas"));
        r.setMezcla((Float)payload.get("mezcla"));
        r.setHumedadAire((Float)payload.get("humedadAire"));
        r.setTempEntrada((Float)payload.get("tempEntrada"));
        r.setTempSalida((Float)payload.get("tempSalida"));
        r.setPresionEntrada((Float)payload.get("presionEntrada"));
        r.setPresionSalida((Float)payload.get("presionSalida"));
        r.setTime((Date)payload.get("time"));
        Ingreso i = new Ingreso();
        i.setId((Integer)payload.get("ingreso"));
        r.setIngreso(i);
        return r;
    }
}
