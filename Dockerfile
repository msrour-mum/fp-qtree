FROM openjdk:8
ADD target/fp-qtree.jar  fp-qtree.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fp-qtree.jar"]