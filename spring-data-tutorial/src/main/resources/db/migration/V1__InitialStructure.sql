CREATE TABLE vehicle (
	id INT(11) NOT NULL AUTO_INCREMENT,
	car_class VARCHAR(50) NULL DEFAULT NULL,
	price FLOAT NULL DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE booking (
	reservation_number VARCHAR(50) NOT NULL,
	vehicle_id INT(11) NOT NULL,
	pickup_date TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (reservation_number),
	INDEX VEHICLE_FK (vehicle_id),
	CONSTRAINT VEHICLE_FK FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);

--Vehicle 1
INSERT INTO vehicle (car_class, price) values ('EDCRMD', 134);
--Vehicle 2
INSERT INTO vehicle (car_class, price) values ('DDSERD', 251);
--Vehicle 3
INSERT INTO vehicle (car_class, price) values ('XXDEDR', 300); 

INSERT INTO booking (reservation_number, vehicle_id, pickup_date) values ('ASDF322342A32', 1, '2014-01-01 12:00:00');
INSERT INTO booking (reservation_number, vehicle_id, pickup_date) values ('DDEFA342J423J', 1, '2014-01-15 12:00:00');
INSERT INTO booking (reservation_number, vehicle_id, pickup_date) values ('FF3AF3f3ASDFA', 3, '2014-01-03 12:00:00');

-- Any new vehicle will start with the id 100
ALTER TABLE vehicle AUTO_INCREMENT = 100;

