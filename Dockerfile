FROM amazoncorretto:17.0.9
EXPOSE 8080
ADD /build/libs/finances-0.0.1.jar finances.jar
ENTRYPOINT ["java", "-jar", "/finances.jar"]