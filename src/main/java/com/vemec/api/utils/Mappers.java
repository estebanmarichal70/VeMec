package com.vemec.api.utils;

import com.vemec.api.constants.Alerta;
import com.vemec.api.constants.Estado;
import com.vemec.api.constants.Sexo;
import com.vemec.api.models.centro.Centro;
import com.vemec.api.models.paciente.Paciente;
import com.vemec.api.models.patologias_wrapper.PatologiasWrapper;
import com.vemec.api.models.ingreso.Ingreso;
import com.vemec.api.models.reporte.Reporte;
import com.vemec.api.models.sala.Sala;
import com.vemec.api.models.usuario.Usuario;
import com.vemec.api.models.vemec.VeMec;
import org.json.JSONObject;

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

    public static Sala mapToSala(Map<String, Object> payload, Sala u) {
        if (payload.get("nombre") != null) {
            u.setNombre(payload.get("nombre").toString());
        }
        if (payload.get("capacidad") != null) {
            u.setCapacidad((Integer) payload.get("capacidad"));
        }
        if (payload.get("centro") != null) {
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


        if (payload.get("sexo") != null) {
            switch (payload.get("sexo").toString()) {
                case "FEMENINO": {
                    u.setSexo(Sexo.FEMENINO);
                    break;
                }
                case "MASCULINO": {
                    u.setSexo(Sexo.MASCULINO);
                    break;
                }
            }
        }


        return u;
    }

    public static Ingreso mapToIngreso(Map<String, Object> payload, Ingreso i) throws ParseException {
        if (payload.get("causa") != null) {
            i.setCausa(payload.get("causa").toString());
        }
        if (payload.get("estado") != null) {
            switch (payload.get("estado").toString()) {
                case "ESTABLE": {
                    i.setEstado(Estado.ESTABLE);
                    break;
                }
                case "CRITICO": {
                    i.setEstado(Estado.CRITICO);
                    break;
                }
                case "INTERMEDIO": {
                    i.setEstado(Estado.INTERMEDIO);
                    break;
                }
                case "SANO": {
                    i.setEstado(Estado.SANO);
                    break;
                }
                case "DIFUNTO": {
                    i.setEstado(Estado.DIFUNTO);
                    break;
                }
            }
        }
        if (payload.get("fechaEgreso") != null) {
            try {
                i.setFechaEgreso(Utils.parseToSqldate((String) payload.get("fechaEgreso")));
            } catch (Exception e) {
                throw e;
            }
        }
        if (payload.get("fechaIngreso") != null) {
            try {
                i.setFechaIngreso(Utils.parseToSqldate((String) payload.get("fechaIngreso")));
            } catch (Exception e) {
                throw e;
            }
        }
        if (payload.get("sala") != null) {
            Sala u = new Sala();
            u.setId((Integer) payload.get("sala"));
            i.setSala(u);
        }
        if (payload.get("vemec") != null) {
            VeMec vm = new VeMec();
            vm.setId((Integer) payload.get("vemec"));
            i.setVemec(vm);
        }
        if (payload.get("paciente") != null) {
            Paciente p = new Paciente();
            p.setId((Integer) payload.get("paciente"));
            i.setPaciente(p);
        }

        return i;
    }

    public static Reporte mapToReporte(JSONObject payload, Reporte r) throws Exception {

        if (payload.get("presionMaxima") != null) {
            double val = ((Number)payload.get("presionMaxima")).doubleValue();
            r.setPresionMaxima(val);
        }

        if (payload.get("presionMinima") != null) {
            double val = ((Number)payload.get("presionMinima")).doubleValue();
            r.setPresionMinima(val);
        }

        if (payload.get("volGas") != null) {
            double val = ((Number) payload.get("volGas")).doubleValue();
            r.setVolGas(val);

        }

        if (payload.get("frecGas") != null) {
            double val = ((Number)payload.get("frecGas")).doubleValue();
            r.setFrecGas(val);

        }

        if (payload.get("mezcla") != null) {
            double val = ((Number)payload.get("mezcla")).doubleValue();
            r.setMezcla(val);

        }

        if (payload.get("humedadAire") != null) {
            double val = ((Number)payload.get("humedadAire")).doubleValue();
            r.setHumedadAire(val);

        }

        if (payload.get("tempEntrada") != null) {
            double val = ((Number)payload.get("tempEntrada")).doubleValue();
            r.setTempEntrada(val);

        }

        if (payload.get("tempSalida") != null) {
            double val = ((Number)payload.get("tempSalida")).doubleValue();
            r.setTempSalida(val);

        }

        if (payload.get("presionEntrada") != null) {
            double val = ((Number)payload.get("presionEntrada")).doubleValue();
            r.setPresionEntrada(val);

        }

        if (payload.get("presionSalida") != null) {
            double val = ((Number)payload.get("presionSalida")).doubleValue();
            r.setPresionSalida(val);
        }

        if (payload.get("alerta") != null) {
            switch (payload.get("alerta").toString()) {
                case "BATERIA": {
                    r.setAlerta(Alerta.BATERIA);
                    break;
                }
                case "PULSACIONES": {
                    r.setAlerta(Alerta.PULSACIONES);
                    break;
                }
                case "PULSACIONES_BATERIA": {
                    r.setAlerta(Alerta.PULSACIONES_BATERIA);
                    break;
                }
                case "ESTABLE": {
                    r.setAlerta(Alerta.ESTABLE);
                    break;
                }
            }
        }
        if (payload.get("time") != null) {
            try {
                long t = (Long) payload.get("time");
                r.setTime(Utils.parseToSqldate(t));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /*if (payload.get("ingreso") != null) {
            Ingreso i = new Ingreso();
            i.setId((Integer) payload.get("ingreso"));
            r.setIngreso(i);
        }*/

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

        if (payload.get("bateria") != null){
            r.setBateria((Boolean) payload.get("bateria"));
            r.setNivelBateria((Integer) payload.get("nivelBateria"));
        }

        if (payload.get("ppm") != null){
            r.setPpm((Integer) payload.get("ppm"));
        }
        return r;
    }

    public static VeMec mapToVeMec(Map<String, Object> payload, VeMec v) {
        if (payload.get("marca") != null) {
            v.setMarca(payload.get("marca").toString());
        }
        if (payload.get("modelo") != null) {
            v.setModelo(payload.get("modelo").toString());
        }
        if (payload.get("estado") != null) {
            v.setEstado((Boolean) payload.get("estado"));
        }
        if (payload.get("sala") != null) {
            Sala u = new Sala();
            u.setId((Integer) payload.get("sala"));
            v.setSala(u);
        }
        return v;
    }

    public static void mapToUsuario(Map<String, String> payload, Usuario u) {
        if (payload.get("username") != null) {
            u.setUsername(payload.get("username"));
        }
        if (payload.get("imagen") != null) {
            u.setImage(payload.get("imagen"));
        }
        if (payload.get("password") != null) {
            u.setPassword(payload.get("password"));
        }
    }
}
