FROM eclipse-temurin:17-jdk

LABEL maintainer="http://www.uv.es/twcam"

RUN apt-get update && apt-get install -y mysql-client

WORKDIR /PruebaMok

COPY target/prueba-mok-0.0.1-SNAPSHOT.jar PruebaMok.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "PruebaMok.jar"]