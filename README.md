To set up project, edit application.properties to use database of choice with login information. 
It is currently pointing to a mysql database called library.
You can choose to load seed data by editing the data-mysql.sql script.
To run this project, run "./mvnw spring-boot:run" from the command line.

Then you can add, edit, delete and view resources.

I only added one test case so far because I ran out of time. For future test cases, I would test each controller 
and each method within the controllers, making sure to test the happy path, null inputs, and the case when the 
resource queried doesn't exist.

