FROM eclipse-temurin:17-jdk

LABEL maintainer="http://www.uv.es/twcam"

RUN apt-get update && apt-get install -y mysql-client

WORKDIR /PruebaDoubleV

COPY target/prueba-doublev-0.0.1-SNAPSHOT.jar PruebaDoubleV.jar
COPY wait-for-it.sh /wait-for-it.sh

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "PruebaDoubleV.jar"]