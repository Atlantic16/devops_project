FROM  maven:3.8.4-openjdk-11
COPY pom.xml .
COPY . . 
RUN mvn -B package -DskipTests
CMD java -cp target/devopsProject-1.0-SNAPSHOT.jar:./target/dependency/commons-csv-1.10.0.jar org.example.devopsProject