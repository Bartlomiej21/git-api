# Read Me First

The following is the official readme file of this project:

# Getting Started

* You need Java version equal or greater than 17 to use this application, as well as Docker. You should also have 
a program to send requests, f.ex. Postman.
* Go to the project folder. Run `mvnw.cmd package` to build package and `docker compose up` so the docker will 
start necessary containers, including database. If you have Maven installed, you can use `mvn package` to build
package. 
* After starting rest-api and database, use Postman or other program to request GET 
at http://localhost:8080/users/{} where login is the GitHub login you want to check.
* If you want to check database entries use a program like DBeaver to connect to it.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)