# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Set environment variables
ENV TZ=Asia/Seoul
ENV APP_HOME=/usr/app/

# Create application directory
WORKDIR $APP_HOME

# Copy the jar file into the container
COPY build/libs/srlk-slrams-api-1.0-jar-with-dependencies.jar app.jar

# Copy the configuration files into the container
COPY src/main/resources/application.properties .
COPY src/main/resources/application-test.properties .
COPY src/main/resources/application-prod.properties .
COPY src/main/resources/logback.xml .

# Set the active profile to 'docker' by default for Docker container
ENV SPRING_PROFILES_ACTIVE=docker

# Run the application when the container launches
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar app.jar"]
