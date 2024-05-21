# prueba mok

para la correcta utilizacion del microservicio se debe una vez clonado, ejecutar en la raiz del proyecto el comando

docker-compose up --build

Luego de tener los contenedores en ejecucion, unicamente bastara con ejecutar los endpoint en postman para validar su correcto funcionamiento.

### Body de los json para el correcto funcionamiento de los endpoints del crud

Endpoint user

{
"name": "christian",
"surname": "sanchez",
"email": "cristaker@hotmail.com"
}

Endpoint posts

{
"title": "insidious",
"user": {
"id": 1
},
"category": {
"id": 1
},
"comments": [
{
"text": "Terrorifica"
},
{
"text": "horrorosamente miedosa"
}
]
}

endpoint comments

{
"text": "mala",
"postTitle":"depredador 2"
}

Endpoint categories

{
"name":"comedia romantica"
}

url para la paginacion desde el navegador
http://localhost:8001/get-paginated-pokemon-data?page=0&size=20