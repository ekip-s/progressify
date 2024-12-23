FROM openjdk:21.0.2-jdk-slim

WORKDIR /app

COPY pom.xml /app/
COPY master_data /app/master_data/
COPY exception_data /app/exception_data/

RUN mvn clean install

CMD ["java", "-jar", "/app/module1/target/my-app.jar"]