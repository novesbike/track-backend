FROM maven:3.6-jdk-11-slim AS build

WORKDIR /workspace

COPY pom.xml /workspace

COPY src /workspace/src

RUN mvn clean package -DskipTests

########### RUN APPLICATION ##############
FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.11_9

WORKDIR /app

COPY --from=build /workspace/target/*.jar /app/application.jar

ENV PROFILE=test
ENV DB_URL=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=noves-bike
ENV DB_USERNAME=root
ENV DB_PASSWORD=root
ENV JWT_SECRET=SecretKeyToGenJWTs

EXPOSE 8080

CMD ["java", "-Xms128m", "-Xmx256m", "-jar", "application.jar"]