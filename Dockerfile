# Step 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .

# 🚀 यहाँ हम ./mvnw की जगह सीधे ग्लोबल mvn का इस्तेमाल कर रहे हैं
RUN mvn clean package -DskipTests

# Step 2: Run the application using OpenJDK
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
