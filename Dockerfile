FROM openjdk:17
EXPOSE 8080
ADD target/gocery-booking-service.jar gocery-booking-service.jar
ENTRYPOINT ["java", "-jar", "/gocery-booking-service.jar"]