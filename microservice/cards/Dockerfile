FROM apache/beam_java17_sdk as build
MAINTAINER tantn
COPY build/libs/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/cards-0.0.1-SNAPSHOT.jar"]