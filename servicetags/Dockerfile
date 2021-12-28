FROM openjdk:11

WORKDIR /opt/server
COPY ./target/servicetags-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "server.jar"]