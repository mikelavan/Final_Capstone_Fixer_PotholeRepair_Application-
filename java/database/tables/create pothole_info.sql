CREATE TABLE pothole_information (
        id SERIAL,
        date_created DATE NOT NULL DEFAULT CURRENT_DATE,
        longitude FLOAT NOT NULL,
        latitude FLOAT NOT NULL,
        address VARCHAR(256),
        severity INT,
        picture VARCHAR(256)
);