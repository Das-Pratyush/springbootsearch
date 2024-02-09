FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/SearchAPIMySQL-0.0.1-SNAPSHOT.jar /app/app.jar

ENV DATABASE_URL=jdbc:mysql://localhost:3306/electronics
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=dasa

CMD ["java", "-jar", "app.jar"]

