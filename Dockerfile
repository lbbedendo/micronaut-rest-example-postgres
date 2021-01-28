FROM openjdk:14-alpine

ENV TZ UTC+3

COPY build/libs/*-all.jar app.jar

EXPOSE 8080

CMD java -Xmx512m -jar app.jar