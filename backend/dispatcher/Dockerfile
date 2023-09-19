FROM eclipse-temurin:17-jdk-alpine

COPY build/libs/dispatcher*.jar /opt/dispatcher.jar

ENTRYPOINT ["java","-jar","/opt/dispatcher.jar"]