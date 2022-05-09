FROM openjdk:8-alpine
ADD target/proyecto-cuentas-pichincha-hugomora-1.0.0-SNAPSHOT.jar D:/Artefacto/app.jar
ENTRYPOINT ["java", "-jar", "D:/Artefacto/app.jar"]