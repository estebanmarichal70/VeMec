# Procesador de reportes obtenidos por VeMec.

- Servicio RESTful, hecho con Spring-Boot.
- Basado en MVC
- Comunicacion con BD mediante Hibernate
- Tomcat embebido

## Guia de utilizacion.

---

Esta pensado para ser compilado con JDK 11 por lo tanto, lo ideal es tenerlo instalado previamente.

Para ejecutar:

```
    java -jar nombre.
```

## Endpoints

**La ruta base es /api/v1**

### **Operaciones con Centros**

| Verbo HTTP | Endpoint                           | Descripcion                                                                                                                                                                                              |
| ---------- | ---------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| POST       | /centro                            | Crea un nuevo centro a partir del objeto JSON recibido en request (ver ejemplo debajo). Le asigna un identificador autogenerado (id).                                                                    |
| GET        | /centro?page=:pagina&limit=:limite | Obtiene todos los centros que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de centros para la misma.                                                     |
| GET        | /centro/:id                        | Obtiene un centro especifico, basado en el parametro id brindado en la URL.                                                                                                                              |
| PUT        | /centro/:id                        | Actualiza un centro especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto. |
| DELETE     | /centro/:id                        | Elimina un centro especifico, basado en el parametro id brindado en la URL.                                                                                                                              |

---

Objeto JSON mencionado arriba relacionado al centro:

```json
{
  "nombre": "Hospital Britanico",
  "direccion": "Av. Italia 2420, Montevideo",
  "codigo": "UY-MO"
}
```

Por `codigos de geolocalizacion`, ver siguiente referencia:
https://es.wikipedia.org/wiki/ISO_3166-2:UY.

---

### **Operaciones con Salas**

| Verbo HTTP | Endpoint                         | Descripcion                                                                                                                                                                                                                                      |
| ---------- | -------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| POST       | /sala                            | Crea una nueva Sala a partir del objeto JSON recibido en request (ver ejemplo debajo). Esta sala debe estar asociada a un centro previamente creado (se debe brindar el identificador de este). Se le asigna un identificador autogenerado (id). |
| GET        | /sala?page=:pagina&limit=:limite | Obtiene todas las Salas de todos los centros que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de salaes para la misma.                                                                           |
| GET        | /sala/:id                        | Obtiene una Sala especifica, basado en el parametro id brindado en la URL.                                                                                                                                                                       |
| PUT        | /sala/:id                        | Actualiza una Sala especifica, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto.                                          |
| DELETE     | /sala/:id                        | Elimina una Sala especifica, basado en el parametro id brindado en la URL.                                                                                                                                                                       |

---

Objeto JSON mencionado arriba relacionado a la sala:

```json
{
  "nombre": "Sala 1",
  "capacidad": 6,
  "centro": 1
}
```

---

### **Operaciones con VeMecs**

| Verbo HTTP | Endpoint                          | Descripcion                                                                                                                                                                                                                                      |
| ---------- | --------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| POST       | /vemec                            | Crea un nuevo VeMec a partir del objeto JSON recibido en request (ver ejemplo debajo). Este VeMec debe estar asociado a una Sala previamente creada (se debe brindar el identificador de esta). Se le asigna un identificador autogenerado (id). |
| GET        | /vemec?page=:pagina&limit=:limite | Obtiene todos los VeMec de todas las salaes que hay guardadas en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de VeMecs para la misma.                                                                            |
| GET        | /vemec/:id                        | Obtiene un VeMec especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                       |
| PUT        | /vemec/:id                        | Actualiza un VeMec especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto.                                          |
| DELETE     | /vemec/:id                        | Elimina un VeMec especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                       |

---

Objeto JSON mencionado arriba relacionado al VeMec:

```json
{
  "marca": "Puritan Bennett",
  "modelo": "980 Ventilator",
  "estado": false, 
  "sala": 1
}
```
El estado define si esta en uso o no el VeMec.

---

### **Operaciones con Pacientes**

| Verbo HTTP | Endpoint                             | Descripcion                                                                                                                                                                                                |
| ---------- | ------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| POST       | /paciente                            | Crea un nuevo Paciente a partir del objeto JSON recibido en request (ver ejemplo debajo). El identificador brindado debe ser el documento del mismo (ej: cedula de identidad).                             |
| GET        | /paciente?page=:pagina&limit=:limite | Obtiene todos los Pacientes que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de Pacientes para la misma.                                                   |
| GET        | /paciente/:id                        | Obtiene un Paciente especifico, basado en el parametro id brindado en la URL.                                                                                                                              |
| PUT        | /paciente/:id                        | Actualiza un Paciente especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto. |
| DELETE     | /paciente/:id                        | Elimina un Paciente especifico, basado en el parametro id brindado en la URL.                                                                                                                              |

---

Objeto JSON mencionado arriba relacionado al Paciente:

```json
{
  "id": 40001112,
  "nombre": "Rolando",
  "apellido": "Casiha Goalgo",
  "edad": 51,
  "patologias": ["diabetes", "asma"]
}
```

---

### **Operaciones con Ingresos**

| Verbo HTTP | Endpoint                            | Descripcion                                                                                                                                                                                                                                                                                |
| ---------- | ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| POST       | /ingreso                            | Crea un nuevo Ingreso a partir del objeto JSON recibido en request (ver ejemplo debajo). Este Ingreso debe estar asociado a una sala, un Paciente y un VeMec previamente creadas (se debe brindar el identificador de cada una de estas). Se le asigna un identificador autogenerado (id). |
| GET        | /ingreso?page=:pagina&limit=:limite | Obtiene todos los Ingresos que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de Ingresos para la misma.                                                                                                                                     |
| GET        | /ingreso/:id                        | Obtiene un Ingreso especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                                                               |
| PUT        | /ingreso/:id                        | Actualiza un Ingreso especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto.                                                                                  |
| DELETE     | /ingreso/:id                        | Elimina un Ingreso especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                                                               |

---

Objeto JSON mencionado arriba relacionado al Paciente:

```json
{
  "causa": "Dolor de garganta",
  "estado": "ESTABLE",
  "sala": 1,
  "vemec": 1,
  "paciente": 40001112,
  "fechaIngreso": "2020-03-26 15:05:42"
}
```

Ver posibles casos para Estado en Enum mostrado en Diagrama.

---

### **Operaciones con Reporte**

| Verbo HTTP | Endpoint                            | Descripcion                                                                                                                                                                                                                                            |
| ---------- | ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| POST       | /reporte                            | Crea un nuevo Reporte a partir del objeto JSON recibido en request (ver ejemplo debajo). Este Reporte debe estar asociado a un Ingreso previamente creado (se debe brindar el identificador de este). Se le asigna un identificador autogenerado (id). |
| GET        | /reporte?page=:pagina&limit=:limite | Obtiene todos los Reportes que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de Ingresos para la misma.                                                                                                 |
| GET        | /reporte/:id                        | Obtiene un Reporte especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                           |
| PUT        | /reporte/:id                        | Actualiza un Reporte especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto.                                              |
| DELETE     | /reporte/:id                        | Elimina un Reporte especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                           |

---

Objeto JSON mencionado arriba relacionado al Paciente:

```json
{
    "time": "2020-03-26 15:10:42",
    "presionMaxima": 20.73,
    "presionMinima": 10.26,
    "unidadPresion": "Pa",
    "volGas": 200.50,
    "unidadVolumen": "cm3",
    "frecGas": 20.5,
    "unidadFrecuencia": "Hz",
    "mezcla": 0.5,
    "humedadAire": 0.6,
    "unidadHumedad": "%",
    "tempEntrada": "11.3",
    "tempSalida": "18.9",
    "unidadTemp": "°C"
}
```
El formato de la fecha es "yyyy-MM-dd HH:mm:ss".
***
