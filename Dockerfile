# Define a imagem base
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app
COPY target/EtapaEncontrar-0.0.1-SNAPSHOT.jar /app/EtapaEncontrar-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "EtapaEncontrar-0.0.1-SNAPSHOT.jar"]
