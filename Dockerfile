FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/securecryptor.jar /app/securecryptor.jar

ENTRYPOINT ["java", "-jar", "/app/securecryptor.jar"]
