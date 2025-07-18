# etapa 1: build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY mvnw pom.xml ./
COPY .mvn .mvn
RUN chmod +x mvnw && ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests

# etapa 2: runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
ARG JAR=target/*.jar
COPY --from=build /app/${JAR} app.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]
