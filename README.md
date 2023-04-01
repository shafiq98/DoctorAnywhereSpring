# DoctorAnywhere - Interns Take Home Assignment
**Reference**: _https://github.com/shafiq98/DoctorAnywhereSpring_

---
## Assignment
Building a Simple RESTful API with Java
## Objective
To build a simple RESTful API using Java and Spring Boot.

## Notes
1. I was not entirely clear on how security could be implemented here, from a business perspective. (Perhaps to only allow users who create a task to remove it?)
   1. Implementing a user class that are each allocated roles seemed too far from the scope of the original assignment so I did not wish to make that assumption and create such a system
2. A creationDate and completedDate was added to the Task object, simply for usability purposes
   1. It would make looking at task history easier, rather than just relying on the task id
3. Postgres is used by default in this system because that was the database I was most comfortable with developing in initially, but MySQL was integrated in using a separate docker-compose file, which satisfies the project's requirement
4. Docker image is built locally during docker-compose, for ease of development. DockerHub image is also available @ **shafiq98/doctoranywhere-spring:1**.
----
## Instructions
### Running Locally
1. Ensure there is a valid PostgreSQL or MySQL instance configured. For example:
   1. _A database called doctoranywhere_
   2. _A user called postgres_
   3. _User's password is postgres_
2. Set the connection string, username and password as environment variables. For example:
   1. _SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/doctoranywhere;_
   2. _SPRING_DATASOURCE_USERNAME=postgres;_
   3. _SPRING_DATASOURCE_PASSWORD=postgres_
      1. Note: Username and password are set as postgres even for MySQL instances, just for this example

```bash
mvn clean install spring-boot:run
```
---
### Running using Docker
**Running with PostgreSQL**
```bash\
$ docker-compose up --build -d
$ docker-compose down -v
```
**Running with MySQL**
```bash\
$ docker-compose -f docker-compose-mysql up --build -d
$ docker-compose down -v
```
---
## Expected Output
**POST** Request from Postman
```shell
{
    "id": 1,
    "title": "Title 2",
    "description": "Description 2",
    "createdDate": "2023-04-01",
    "complete": false,
    "completedDate": null
}
```
**GET ALL** Request from Postman
```shell
[
    {
        "id": 1,
        "title": "Title 2",
        "description": "Description 2",
        "createdDate": "2023-04-01",
        "complete": false,
        "completedDate": null
    }
]
```

**PUT** Request from Postman
```shell
{
    "id": 1,
    "title": "Title 1",
    "description": "Updated Description 2",
    "createdDate": "2023-04-01",
    "complete": true,
    "completedDate": "2023-04-01"
}
```

**GET ALL** Request from Postman _after PUT_
```shell
[
    {
        "id": 1,
        "title": "Title 1",
        "description": "Updated Description 2",
        "createdDate": "2023-04-01",
        "complete": true,
        "completedDate": "2023-04-01"
    }
]
```

**DELETE** Request from Postman _after PUT_
```shell
{
    "id": 1,
    "title": "Title 1",
    "description": "Updated Description 2",
    "createdDate": "2023-04-01",
    "complete": true,
    "completedDate": "2023-04-01"
}
```

**GET ALL** Request from Postman _after DELETE_
```shell
[]
```

