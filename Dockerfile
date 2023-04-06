FROM openjdk:17-jdk-alpine
WORKDIR /app
EXPOSE 8080
ADD target/learning-center-0.0.1-SNAPSHOT.jar /learning-center.jar
ENTRYPOINT ["java","-jar","/learning-center.jar"]