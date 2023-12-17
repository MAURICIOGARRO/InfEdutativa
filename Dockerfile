FROM maven:3.8.4-openjdk-8-slim AS builder
WORKDIR /usr/src/app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn package

FROM tomcat:jre8-temurin
COPY --from=builder /usr/src/app/target/*.war /usr/local/tomcat/webapps/
ARG RAILWAY_TCP_APPLICATION_PORT
ENV CATALINA_OPTS="-Dcatalina.http.port=$RAILWAY_TCP_APPLICATION_PORT"
EXPOSE $RAILWAY_TCP_APPLICATION_PORT
CMD ["catalina.sh", "run"]