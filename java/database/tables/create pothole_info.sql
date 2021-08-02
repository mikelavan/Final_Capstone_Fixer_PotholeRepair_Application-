CREATE TABLE pothole_information (
        id SERIAL,
        date_created DATE NOT NULL DEFAULT CURRENT_DATE,
        longitude FLOAT NOT NULL,
        latitude FLOAT NOT NULL,
        address VARCHAR(256),
        severity INT,
        picture VARCHAR(256)
);

INSERT INTO pothole_information (longitude, latitude, severity) VALUES (44.7128, 74.0060, 3);

SELECT * FROM pothole_information;