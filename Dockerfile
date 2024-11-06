# Start from a Tomcat base image
FROM tomcat:10.0-jdk21

# Set working directory inside container
WORKDIR /usr/local/tomcat

# Copy your application WAR file into the Tomcat webapps directory
# Replace 'your-app.war' with the actual WAR file of your application
COPY path/to/TechBlog.war webapps/

# Expose the port on which Tomcat will run
EXPOSE 8081

# Start Tomcat server
CMD ["catalina.sh", "run"]
