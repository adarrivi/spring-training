Execute the following scripts in your database:

CREATE DATABASE spring_training_exercise;
GRANT ALL ON spring_training_exercise.* TO 'training'@'localhost' IDENTIFIED BY 'training';

Run from command line (or intellij) the following maven commands to set up the database
mvn compile
mvn flyway:migrate


You need to create the domain objects, daos, services and controllers to make the tests run.

Basically there are 3 operations to implement:
VehicleSearch: returns all the vehicles in the database.
Booking Creation: creates a new booking
Booking Search: returns the details of a booking by its reservation number.

The Json requests/responses have been already created in the rest.dto folder: BookingSearchRs, NewBookingRq/Rs and VehicleSearchRs.

After you've made your changes, run the application in a Tomcat server and also run the tests under exercise.client to see if they are successful.