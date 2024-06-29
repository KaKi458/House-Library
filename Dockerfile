FROM openjdk:21-slim

WORKDIR /app

COPY target/house-library-0.2.0.jar /app/house-library.jar

ENTRYPOINT [ "java", "-jar", "house-library.jar" ]