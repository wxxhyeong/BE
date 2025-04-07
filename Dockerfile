FROM tomcat:9-jdk17-openjdk-slim

COPY build/libs/BE-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
