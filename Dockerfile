FROM tomcat:9.0-jre17
COPY build/libs/LogSystem-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/logsystem.war