# -----------------------------
# Build Stage
# -----------------------------
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files into the container
COPY . .

# Build the project (skip tests, since Jenkins runs them separately)
RUN mvn clean package -DskipTests

# -----------------------------
# Runtime Stage (lighter image)
# -----------------------------
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy packaged jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# (Optional) Run jar if your project is an application
# If it's only a test automation repo, you can remove this CMD line
CMD ["java", "-jar", "app.jar"]
