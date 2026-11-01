FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn -B -ntp clean package

FROM tomcat:11.0-jdk25-temurin

RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /app/target/astro.war /usr/local/tomcat/webapps/ROOT.war

# Configure DB_URL, DB_USER e DB_PASSWORD no ambiente do Render.
EXPOSE 10000

CMD ["sh", "-c", "sed -i \"s/port=\\\"8080\\\"/port=\\\"${PORT:-10000}\\\"/\" conf/server.xml && exec catalina.sh run"]
