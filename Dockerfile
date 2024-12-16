FROM openjdk:17-jdk-slim
WORKDIR /app
##COPY target/customer-service.jar customer-service.jar
COPY ./target/bankservice-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
