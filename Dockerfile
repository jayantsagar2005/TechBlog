FROM ubuntu:latest
LABEL authors="hp"

ENTRYPOINT ["top", "-b"]

# Step 1: Use Tomcat as the base image
FROM tomcat:10.0-jdk21

# Step 2: Set environment variables
ENV JAVA_OPTS="-Djava.awt.headless=true -Xmx512m -XX:+UseConcMarkSweepGC"

# Step 3: Copy WAR file to Tomcat's webapps directory
# Replace 'your-project.war' with the name of your WAR file
COPY target/TechBlog.war /usr/local/tomcat/webapps/

# Step 4: Expose the default Tomcat port
EXPOSE 8081

# Step 5: Start Tomcat server
CMD ["catalina.sh", "run"]
