FROM eclipse-temurin:19

WORKDIR /app

COPY target/House-Library-0.1.0.jar /app/app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]