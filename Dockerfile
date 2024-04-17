FROM openjdk:8
COPY --from=build /target/devopsProject-1.0-SNAPSHOT.jar DataFrame.jar
COPY --from=build /target/devopsProject-1.0-SNAPSHOT-sources.jar .
COPY --from=build /target/dependency/ .
RUN mkdir data
COPY /data /data
CMD java -cp DataFrame.jar:commons-csv-1.10.0.jar org.example.devopsProject