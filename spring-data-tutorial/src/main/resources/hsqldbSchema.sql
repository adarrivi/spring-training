CREATE TABLE vehicle (
	id INT generated by default as identity (start with 10),
	carClass VARCHAR(255) NOT NULL,	
	price  FLOAT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE booking (
	id INT generated by default as identity (start with 10),
	vehicle_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);
	