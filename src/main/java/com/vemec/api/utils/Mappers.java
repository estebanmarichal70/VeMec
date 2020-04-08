package com.vemec.api.utils;

import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.ubicacion.Ubicacion;

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
        u.setNombre(payload.get("nombre").toString());
        u.setCapacidad((Integer) payload.get("capacidad"));
        Centro c = new Centro();
        c.setId((Integer) payload.get("centro"));
        u.setCentro(c);
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
}
