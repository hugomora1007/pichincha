FROM openjdk:8-alpine
COPY target/proyecto-cuentas-pichincha-hugomora-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]