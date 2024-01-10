# Use an official OpenJDK runtime as a parent image
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY target/your-spring-boot-app.jar /app/your-spring-boot-app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "your-spring-boot-app.jar"]
