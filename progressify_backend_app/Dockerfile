FROM openjdk:21-jdk-slim

RUN apt-get update && apt-get install -y \
    maven \
    && apt-get clean

WORKDIR /app

COPY progressify_backend_app/pom.xml /app/
COPY progressify_backend_app/master_data /app/master_data/
COPY progressify_backend_app/exception_data /app/exception_data/

RUN mvn clean install

CMD ["java", "-jar", "/app/master_data/target/master_data-1.0-SNAPSHOT.jar"]