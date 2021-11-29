FROM openjdk:8-jdk-slim
COPY "./target/LineaIII-0.0.1-SNAPSHOT.jar" "linea.jar"
EXPOSE 9090
ENTRYPOINT [ "java","-jar","linea.jar" ]