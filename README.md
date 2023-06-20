# Read Me First

The following is the official readme file of this project:

# Getting Started

* You need Java version equal or greater than 17 to use this application, as well as Docker. You should also have 
a program to send requests, f.ex. Postman.
* Go to project folder. Run `mvnw.cmd package` in command terminal to build package. If you have Maven installed, 
you can use `mvn package` to do it.
* Run `docker compose up` in command terminal so the docker will start necessary containers, including database. 
On subsequent uses you can use `docker compose up --build` if you want to make changes to project and build it again. 
* After successfully starting rest-api and database, use Postman or other program to request GET 
at endpoint `http://localhost:8080/users/login` where login is the GitHub login you want to check.
* If you want to check database entries use a program like DBeaver to connect to it.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)