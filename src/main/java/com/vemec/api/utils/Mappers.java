package com.vemec.api.utils;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.ubicacion.Ubicacion;

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
}
