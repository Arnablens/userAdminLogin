  
FROM tomcat:9-jre8
RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/mvcsprings_-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
