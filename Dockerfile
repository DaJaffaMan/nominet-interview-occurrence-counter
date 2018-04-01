FROM openjdk:8-jre-alpine

COPY target/counter-1.0-SNAPSHOT.jar /counter-1.0-SNAPSHOT.jar

RUN java -jar /counter-1.0-SNAPSHOT.jar