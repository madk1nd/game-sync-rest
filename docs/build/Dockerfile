FROM anapsix/alpine-java
ADD ../../target/game-sync-1.0-SNAPSHOT.jar demo.jar
EXPOSE 40000
ENTRYPOINT sleep 5 && java -jar demo.jar