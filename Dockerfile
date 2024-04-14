FROM openjdk:8
COPY . .
CMD java -cp target/classes org.example.devopsProject