FROM apache/beam_java17_sdk as build
MAINTAINER tantn
COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/accounts-0.0.1-SNAPSHOT.jar"]