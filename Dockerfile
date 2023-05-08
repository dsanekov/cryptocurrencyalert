FROM openjdk:17
ADD /target/cryptocurrencyalert-0.0.1-SNAPSHOT.jar cryptocurrencyalert.jar
ENTRYPOINT ["java","-jar","cryptocurrencyalert.jar"]
