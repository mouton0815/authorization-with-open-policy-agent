FROM maven:3.9-eclipse-temurin-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN --mount=type=cache,target=/root/.m2 mvn -f /home/app/pom.xml clean package

FROM eclipse-temurin:17-jre-jammy
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]