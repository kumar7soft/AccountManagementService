# Start with a base image containing Java runtime (adjust if Java 21 image is available)
FROM openjdk:21-jdk-slim as build

# Add Maintainer Info
LABEL maintainer="your-email@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8081

# The application's jar file
ARG JAR_FILE=target/UserManagementService-0.0.1-SNAPSHOT.jar

COPY target/UserManagementService-0.0.1-SNAPSHOT.jar UserManagementService.jar
# Run the jar file
ENTRYPOINT ["java", "-jar", "/UserManagementService.jar"]

# Add the application's jar to the container
#ADD ${JAR_FILE} my-spring-boot-app.jar

# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/UserManagementService.jar"]
