DROP TABLE IF EXISTS pothole_information;

CREATE TABLE pothole_information (
        id SERIAL,
        date_created DATE NOT NULL DEFAULT CURRENT_DATE,
        longitude FLOAT NOT NULL,
        latitude FLOAT NOT NULL,
        address VARCHAR(256),
        severity INT,
        picture VARCHAR(256)
);

INSERT INTO pothole_information (longitude, latitude, severity) VALUES (-75.148619, 39.944040, 3);
INSERT INTO pothole_information (longitude, latitude, severity) VALUES (-75.166952, 39.936435, 3);
INSERT INTO pothole_information (longitude, latitude, severity) VALUES (-75.170456, 39.945308, 3);
INSERT INTO pothole_information (longitude, latitude, severity) VALUES (-75.165260, 39.936435, 3);


