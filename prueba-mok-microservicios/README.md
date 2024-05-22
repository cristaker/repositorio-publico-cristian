# prueba mok

para la correcta utilizacion del microservicio se debe una vez clonado, ejecutar en la raiz del proyecto el comando

instalar mysql en un contenedor con los siguientes comandos

descargar la imagen

docker pull mysql:latest

generar el contenedor de mysql 

docker run -d --name mysql -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=mokDB -p 3306:3306 mysql

en el archivo host de cada maquina con la ip4 de la maquina registrar estos 2 campos

ipmaquina mysql
ipmaquina kafka-service

construir el compose

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