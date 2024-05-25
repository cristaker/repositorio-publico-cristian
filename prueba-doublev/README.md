# Gestión de Tickets y Consulta de Usuarios de GitHub

Este proyecto crea una aplicación con Java, Spring Boot y MySQL que proporciona dos conjuntos de endpoints: uno para la gestión de tickets y otro para la consulta de usuarios de GitHub.

## Requisitos

- Java JDK 17
- Maven
- Docker (opcional, para ejecutar la base de datos MySQL en un contenedor)
- Postman (opcional, para probar los endpoints)

## Ejecución

Para la ejecución de esta aplicación, puedes hacerlo de dos maneras diferentes:
- Clona el repositorio desde la URL: 
- Luego de haber clonado el repositorio, ejecuta el siguiente comando: `docker-compose up --build` en la consola de comandos, asegurate de ubicarte en la raiz del proyecto clonado.
- También puedes levantar la aplicación desde cualquier entorno de desarrollo. Sin embargo, ten en cuenta que, para una ejecución correcta en este caso, debes tener instalado MySQL 8.4 en la máquina local, tenerlo en ejecución y haber creado la base de datos `doublevdb`.

## Pruebas

Una vez que tengas el proyecto en ejecución, puedes realizar pruebas utilizando una herramienta como Postman para probar las APIs.

### Creación de Tickets

Para la creación de un ticket en la base de datos mediante el método POST, se recomienda usar este body de ejemplo:
```json
{
    "usuario": "christian",
    "status": "cerrado"
}

```

Una vez creado el primer registro en base de datos mediante los endpoints, puedes consultarlos todos, consultar tickets individualmente por id, editar y eliminar tickets a tu antojo.

Para la ejecución de la API de consulta de usuarios en GitHub, se recomienda:

- El uso de esta URL http://localhost:8000/api/github/users?query=cristaker para la búsqueda de coincidencias en GitHub, donde "cristaker" será el parámetro de búsqueda.
- Para la búsqueda de la información por username, se recomienda el uso de esta URL http://localhost:8000/api/github/users/cristaker, donde "cristaker" será el username en GitHub.