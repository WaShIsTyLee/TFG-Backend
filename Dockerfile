# Etapa 1: Construcción del .jar
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

# Etapa 2: Ejecución del .jar
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/FlyPark1-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
