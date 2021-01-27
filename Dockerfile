FROM openjdk:14-alpine

ENV TZ UTC+3

COPY build/libs/diecast-collector-api-*-all.jar diecast-collector-api.jar

EXPOSE 8080

CMD java -Xmx512m -jar diecast-collector-api.jar