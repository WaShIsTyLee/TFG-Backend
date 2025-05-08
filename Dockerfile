# Usa una imagen ligera de Java 17 (ideal para Spring Boot)
FROM openjdk:17-jdk-slim

# Define una variable que representa tu archivo JAR
ARG JAR_FILE=target/*.jar

# Copia el JAR desde tu máquina al contenedor
COPY ${JAR_FILE} app.jar

# Comando que ejecuta la app
ENTRYPOINT ["java", "-jar", "/app.jar"]
