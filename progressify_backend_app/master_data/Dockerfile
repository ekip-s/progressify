FROM openjdk:21-jdk-slim AS build

RUN apt-get update && apt-get install -y maven

COPY progressify_backend_app/ /app
WORKDIR /app

RUN mvn clean install -pl master_data -am -DskipTests

FROM openjdk:21-jdk-slim

COPY --from=build /app/master_data/target/master_data-1.0.0.jar /app/master_data-1.0.0.jar

ENTRYPOINT ["java", "-jar", "/app/master_data-1.0.0.jar"]