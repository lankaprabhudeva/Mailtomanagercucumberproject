# Use Maven with JDK 17
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files into the container
COPY . .

# Build the project, allow skipping tests via build arg
ARG SKIP_TESTS=false
RUN if [ "$SKIP_TESTS" = "true" ]; then \
      mvn clean package -DskipTests; \
    else \
      mvn clean package; \
    fi

# ----------------------------------------------------------
# Runtime image (lighter than Maven)
# ----------------------------------------------------------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy compiled app from builder image
COPY --from=build /app/target/*.jar app.jar

# Run the packaged jar
CMD ["java", "-jar", "app.jar"]
