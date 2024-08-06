FROM openjdk:17-jdk-slim

COPY server/build/libs/server-all.jar /usr/local/lib/server.jar

ENTRYPOINT ["java", "-Xms2G", "-Xmx2G", "-jar", "/usr/local/lib/server.jar"]

# Env vars: DB_URL, DB_USER, DB_PASSWORD
