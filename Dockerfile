FROM openjdk
LABEL maintainer="Yar Zar <zar31528@example.com>"
COPY target/spring-api-0.0.1-SNAPSHOT.jar /app/spring-api-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "spring-api-0.0.1-SNAPSHOT.jar"]
