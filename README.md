# DoctorAnywhere - Interns Take Home Assignment

---
## Assignment
Building a Simple RESTful API with Java
## Objective
To build a simple RESTful API using Java and Spring Boot.
## Instructions
1. Create a new Spring Boot project using your favourite IDE. Use Maven or Gradle to manage dependencies.
2. Define a simple data model for a "Task" object that includes the following properties:
   1. id: Long (a unique identifier for the task)
   2. title: String (title of the task)
   3. description: String (description of the task)
   4. completed: Boolean (flag indicating whether the task has been completed)
3. Implement the following RESTful endpoints 
   1. GET /tasks: Get a list of all tasks
   2. POST /tasks: Create a new task
   3. GET /tasks/{id}: Get a single task by ID
   4. PUT /tasks/{id}: Update a task by ID
   5. DELETE /tasks/{id}: Delete a task by ID
4. Use an in-memory data store (e.g., a List or Map) to store the task data.
5. Test your API using Postman or any other REST client to ensure that it works as expected.
6. Use git as the version control for your project.
   
## Bonus points
* Implement error handling for each endpoint.
* Use Spring Data JPA to store the task data in a MySQL database.
* Use Spring Security to add authentication and authorization to the API.
* Can run the application in a container.

## Submission
* The deadline for this assignment is 72 hours from the time this assignment landed in your Inbox. Tick tock tick tock !
* The candidate should submit a zip file containing the following artifacts
  * All the necessary files (Java classes and pom.xml file) required to run the Spring Boot application as a git repo
  * Documentation with at least the steps to run the project as a README.md file.
  * Your assumptions (if any) that would have made during your assignment.
* Please submit by ‘Replying All’ to the email thread which you received this assignment from.
* Your submission should be in a form of a URL link. (Eg; A Google Drive link where we are able to access and download your
* deliverable zip)

---
## TODO
1. Implement error handling as per https://www.toptal.com/java/spring-boot-rest-api-error-handling

----
## Instructions
```bash
$ docker-compose up --build -d
$ docker-compose down -v
```