FROM openjdk:11
EXPOSE 8081
ADD ./dunblog-spring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.profiles.active=pro"]