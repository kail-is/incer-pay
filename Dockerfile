FROM amazoncorretto:21-alpine
EXPOSE 8080
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",
ENTRYPOINT ["java", "-jar", "/app.jar"]