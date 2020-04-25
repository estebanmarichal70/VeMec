# Procesador de reportes obtenidos por VeMec.

- Servicio RESTful, hecho con Spring-Boot.
- Basado en MVC
- Comunicacion con BD mediante Hibernate
- Tomcat embebido

## Guia de utilizacion.

---

Esta pensado para ser compilado con JDK 11 por lo tanto, lo ideal es tenerlo instalado previamente.

Para ejecutar, pararse en la carpeta donde esta el pom:

```
    ./mvnw clean compile spring-boot:run
```
Para compilar y obtener la API empaquetada en un jar ubicado en directorio target:
```
    ./mvnw clean install
```

## Endpoints

**La ruta base es /api/v1**

Se **debe** enviar el token obtenido al autenticar el usuario para poder acceder a los recursos en todos los casos.
Para las rutas /auth o /reporte no precisa enviarlo.

Se debe enviar en el header "Authorization" como Bearer, ejemplo:

```$xslt
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4Nzg4NDI0MCwiaWF0IjoxNTg3ODQ4MjQwfQ.71q4K-hx0zDVKoy8zChdcOp7RJSEU8Vk-_Xp6PyuGjA
```

### **Operaciones con Usuarios**

Entidad que maneja la creación, modificación y eliminación de los centros médicos donde se utiliza la aplicación. Sus atributos son el nombre del centro, su dirección y su código de geolocalización. Además tiene una colección de salas dentro, la cual ofrece ubicaciones y datos de las mismas. 

| Verbo HTTP | Endpoint                           | Descripcion                                                                                                                                                                                              |
| ---------- | ---------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| POST       | /auth/sign_up                      | Crea un nuevo usuario a partir del objeto JSON recibido en request (ver ejemplo debajo). Le asigna un identificador autogenerado (id).                                                                    |
| POST       | /auth/sign_in                      | Se autentica un usuario, basado en los datos enviados en el cuerpo de la peticion. Devuelve un token, con el cual se debe proceder a hacer las peticiones.                                                    |
---

Objeto JSON mencionado arriba relacionado al usuario:

```json
{
  "username": "Tesla",
  "password": "misupercontrasenia",
  "imagen": "data:image/jpeg;base64,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"
}
```

Respuesta
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOsdhZG1pbiIsImV4cCI6jTU4Nzg4NDI0M23iaWF0IjoxNTg3ODQ4MjQwfQ.71q4K-hx0zDVKoy8zChdcOp7RJSEU8Vk-_Xp6PyuGjA"
}
```

---

### **Operaciones con Centros**

Entidad que maneja la creación, modificación y eliminación de los centros médicos donde se utiliza la aplicación. Sus atributos son el nombre del centro, su dirección y su código de geolocalización. Además tiene una colección de salas dentro, la cual ofrece ubicaciones y datos de las mismas. 

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

Entidad que maneja la creación, modificación y eliminación de los salas ubicadas en los centros médicos donde se utiliza la aplicación. Sus atributos son su nombre (puede ser su número de sala), la capacidad de personas que puede almacenar, los VeMecs que tiene y al centro que pertenece.

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

Entidad que maneja la creación, modificación y eliminación de los VeMecs (respiradores mecánicos) utilizados por la aplicación. Aporta datos generales del VeMec a utilizar. Sus atributos son la marca del VeMec, su modelo, si se encuentra en uso o no y la sala a la que pertenece.

Atributos:

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

Entidad que maneja la creación, modificación y eliminación de los pacientes que alguna vez pertenecieron a los centros médicos donde se utiliza la aplicación. Se encarga de guardar sus datos básicos y las veces que ingresó en el hospital. Sus atributos son el nombre, apellido y edad. Cuenta con una colección de patologías y de ingresos del mismo (un historial).

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

Entidad que maneja la creación, modificación y eliminación de los ingresos de los pacientes al centro. Guarda los datos de un ingreso específico del paciente.  Sus atributos son el estado que se encuentra (dado de alta, crítico, etc), la causa por la que se internó, la sala en la que está internado, el VeMec que está utilizando, su fecha de ingreso, fecha de egreso (si es que egresó), el paciente al que pertenece el ingreso y sus reportes.

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

Entidad que maneja la creación, modificación y eliminación de los datos proporcionados por el VeMec. Guarda los datos que brinda los VeMec en uso a un determinado paciente. Sus atributos son presión máxima/mínima, volumen y frecuencia del gas, mezlca de O2, humedad en el ambiente, temperatura entrada/salida, presión entrada/salida, hora en la que se realizó el reporte, ingreso al cual pertenece el reporte y las unidades utilizadas.

| Verbo HTTP | Endpoint                            | Descripcion                                                                                                                                                                                                                                            |
| ---------- | ----------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| POST       | /reporte                            | Crea un nuevo Reporte a partir del objeto JSON recibido en request (ver ejemplo debajo). Este Reporte debe estar asociado a un Ingreso previamente creado (se debe brindar el identificador de este). Se le asigna un identificador autogenerado (id). |
| GET        | /reporte?page=:pagina&limit=:limite | Obtiene todos los Reportes que hay guardados en la Base de Datos consumida. Se debe brindar un numero de pagina y un limite de Ingresos para la misma.                                                                                                 |
| GET        | /reporte/:id                        | Obtiene un Reporte especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                           |
| PUT        | /reporte/:id                        | Actualiza un Reporte especifico, basado en el parametro id brindado en la URL. La nueva informacion debe ser enviada en un objeto JSON en la request. Puede ser actualizacion parcial o total del objeto.                                              |
| DELETE     | /reporte/:id                        | Elimina un Reporte especifico, basado en el parametro id brindado en la URL.                                                                                                                                                                           |

---

Objeto JSON mencionado arriba relacionado al Reporte:

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
