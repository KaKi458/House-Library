FROM openjdk:21-slim

WORKDIR /app

COPY target/home-library-0.2.0.jar /app/home-library.jar

ENTRYPOINT [ "java", "-jar", "home-library.jar" ]