# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

1) Установить postreSQL 15, создать бд с названием "meal_planner_db" (через pgadmin)
2) Скачать архив;
3) В терминале перейти в папку с архивом:       
   * cd /home/username/Downloads
4) Распаковать:
   * unzip mealplanner.zip
5) Перейти в распакованный архив:
   * cd MealPlannerSpringBoot-master
6) Запустить приложение:
   * ./mvnw spring-boot:run
7) Остановить приложение:
   * Ctrl+C

- Чтобы создать .jar:
   * ./mvnw clean package
- Чтобы запустить .jar:
   * java -jar target/mealplanner.jar