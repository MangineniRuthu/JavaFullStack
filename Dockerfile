FROM openjdk:17
EXPOSE 8080
COPY target/learningacademy.jar app.jar
ENTRYPOINT ["java","-java","/app.jar"]