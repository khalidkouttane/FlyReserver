DROP TABLE IF EXISTS booking;

CREATE TABLE booking (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         booking_reference VARCHAR(255) NOT NULL,
                         passenger_id BIGINT NOT NULL,
                         flight_number VARCHAR(255) NOT NULL,
                         departure_airport VARCHAR(255) NOT NULL,
                         destination_airport VARCHAR(255) NOT NULL,
                         departure_time VARCHAR(255) NOT NULL,
                         seat_number VARCHAR(255),
                         gate_closing_time VARCHAR(255),
                         status VARCHAR(255)
);