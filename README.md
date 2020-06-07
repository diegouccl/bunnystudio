# bunnystudio


Hey Bunny Studio this is my test for the problem that you send to me

I've use SpringBoot as Microservices and Vuejs for front end, also I've use postgres sql for database

Springboot is my microservices with a reposittory pattern implemented, there are a lot of thing that we can do but since I had just one day I made the priority.

This is a small diagram of my project:

![](BPMN%202.0.png)

For running this app you would have to clone the repository and make the following steps

For Spring project

1) Clone or download the project
2) cd to backend
2) Install Dependencies with Maven (mvn clean install)
3) Import project to IDE
4) Run the project

mvn spring-boot:run

Note : Inside the project in the application.properties file, the properties:
```
spring.jpa.generate-ddl = true
spring.jpa.hibernate.ddl-auto=create-drop
Automatically the database is going to be creating with the following connection param
```
For Vue project

1) Go inside fontend folder
2) npm install
3) npm run dev


Live demo on aws deployment with elasticbeanstalk and s3 for the front end, also a db runing on a google app engine instance

````
[DEMO on AWS](http://bunnstudio-frontend.s3-website.us-east-2.amazonaws.com) 
````


Diego Romero Bunny Studio Test
