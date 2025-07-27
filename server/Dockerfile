FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21 AS runner
ENV SPRING_PROFILES_ACTIVE=prod
WORKDIR /app
COPY --from=builder /app/target/*.jar .
CMD java -jar *.jar
