FROM maven:3.8.4-openjdk-8-slim AS builder
WORKDIR /usr/src/app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn package

FROM tomcat:jre8-temurin
COPY --from=builder /usr/src/app/target/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]