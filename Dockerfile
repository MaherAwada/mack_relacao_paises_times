FROM maven:3.9.4-eclipse-temurin-17-focal as builder
COPY . /app
RUN mvn -f /app clean package

FROM eclipse-temurin:17.0.8.1_1-jre-jammy
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
COPY --from=builder /app/target/exemplo3-1.0.0.jar /opt/app.jar
