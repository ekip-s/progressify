FROM openjdk:21-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

COPY progressify_backend_app/ /app
WORKDIR /app

RUN mvn clean install -pl kafka_reader -am -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /app/kafka_reader/target/kafka_reader-1.0-SNAPSHOT.jar /app/kafka_reader-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/kafka_reader-1.0-SNAPSHOT.jar"]