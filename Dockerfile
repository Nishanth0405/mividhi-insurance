# Build Stage
# FROM maven:3.8.7-openjdk-21 AS builder
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

COPY . .
RUN mvn clean install -DskipTests

# Run Stage
FROM openjdk:21
WORKDIR /app
COPY --from=builder /app/target/insurance-0.0.1-SNAPSHOT.jar /app/insurance-0.0.1-SNAPSHOT.jar
EXPOSE 80
CMD java -Dspring.profiles.active=default -Dreactor.netty.http.server.accessLogEnabled=true --enable-preview -agentlib:jdwp=transport=dt_socket,server=y,suspend=n -jar "/app/insurance-0.0.1-SNAPSHOT.jar"
