package com.vemec.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// aca metan lo que se pueda reutilizar
// asi no se esta repitiendo
public class Utils {

    public static String ejemplo(String ejemplo){
        return "Mandaste al ejemplo: " +ejemplo;
    }

    public static ResponseEntity mapErrors(Exception e) {
        if(e.getMessage()=="Not Found" || e.getMessage().contains("No class com.vemec.api.models.centro.Centro entity with") || e.getMessage()=="No value present" || e.getMessage().contains("No class com.vemec.api.models.ubicacion.Ubicacion entity"))
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.NOT_FOUND, "No se encontró el Recurso"), null, HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(new ErrorHandler(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
