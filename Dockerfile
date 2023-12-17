FROM maven:3.8.4-openjdk-8-slim AS builder
WORKDIR /usr/src/app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn package

FROM tomcat:jre8-temurin
COPY --from=builder /usr/src/app/target/*.war /usr/local/tomcat/webapps/
ARG PORT
ENV PORT=$PORT
ENV CATALINA_OPTS="-Dcatalina.http.port=$PORT"
CMD ["catalina.sh", "run"]