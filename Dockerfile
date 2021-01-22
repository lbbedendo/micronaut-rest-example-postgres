FROM adoptopenjdk:11.0.9_11-jdk-hotspot-focal

ENV TZ UTC+3

COPY build/libs/diecast-collector-api-*-all.jar diecast-collector-api.jar

EXPOSE 8080

CMD java -Dcom.sun.management.jmxremote -noverify ${JAVA_OPTS} -jar diecast-collector-api.jar