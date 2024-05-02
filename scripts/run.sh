mvn clean package -DskipTests
docker build -f deploy/Dockerfile -t teste .
docker run -it --rm -m 256m -p 8080:8080 teste
