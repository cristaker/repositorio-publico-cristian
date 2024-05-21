FROM eclipse-temurin:17-jdk

LABEL maintainer="http://www.uv.es/twcam"

WORKDIR /ConsumoApiMok

COPY target/consumo-api-mok-0.0.1-SNAPSHOT.jar ConsumoApiMok.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "ConsumoApiMok.jar"]