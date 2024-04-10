FROM openjdk:17
EXPOSE 8080
COPY target/learningacademy.jar javafullstack.jar
ENTRYPOINT ["java","-java","/javafullstack.jar"]