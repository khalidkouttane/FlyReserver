CREATE TABLE passenger (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           email VARCHAR(255) NOT NULL,
                           phone_number VARCHAR(50),
                           document_number VARCHAR(50) NOT NULL
);
