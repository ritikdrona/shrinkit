FROM eclipse-temurin:21.0.7_6-jdk AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21.0.7_6-jre AS runner
WORKDIR /app
COPY --from=builder /app/target/*.jar .
CMD java -jar *.jar
