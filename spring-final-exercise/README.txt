Execute the following scripts in your database:

CREATE DATABASE spring_training_exercise;
GRANT ALL ON spring_training_exercise.* TO 'training'@'localhost' IDENTIFIED BY 'training';

Run from command line (or intellij) the following maven commands
mvn compile
mvn flyway:migrate


