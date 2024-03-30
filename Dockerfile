FROM openjdk:17
COPY target/learningacademy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-java","/app.jar"]