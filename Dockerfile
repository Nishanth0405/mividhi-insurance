FROM ubuntu:20.04
RUN apt-get update
FROM openjdk:21
RUN mvn clean install -DskipTests
ENV ARTIFACTARG=${ARTIFACT}
WORKDIR /
COPY target/insurance-0.0.1-SNAPSHOT.jar	/insurance-0.0.1-SNAPSHOT.jar
EXPOSE 80
RUN echo "-----> before CMD ${ARTIFACTARG}"
CMD java -Dspring.profiles.active=${DEPLOYMENT} -Dreactor.netty.http.server.accessLogEnabled=true --enable-preview -agentlib:jdwp=transport=dt_socket,server=y,suspend=n -jar insurance-0.0.1-SNAPSHOT.jar