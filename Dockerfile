FROM maven:3.8.7
COPY pom.xml .
COPY /target/*.jar .
COPY /target/dependency/ ./lib/
RUN mkdir data
COPY /data /data
CMD java -cp devopsProject-1.0-SNAPSHOT.jar:./lib/commons-csv-1.10.0.jar org.example.devopsProject