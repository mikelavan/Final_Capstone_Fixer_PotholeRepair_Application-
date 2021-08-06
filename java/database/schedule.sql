DROP TABLE IF EXISTS schedule;

CREATE TABLE schedule (
        schedule_id SERIAL,
        pothole_id INT NOT NULL,
        status VARCHAR(256) DEFAULT 'Reported',
        date_reported DATE,
        date_inspected DATE,
        date_repaired DATE,
        
        CONSTRAINT pk_schedule_id PRIMARY KEY (schedule_id),
        CONSTRAINT fk_pothole_id FOREIGN KEY (pothole_id) REFERENCES pothole_information (id)
        )
        
INSERT INTO schedule (pothole_id) VALUES (1);
INSERT INTO schedule (pothole_id) VALUES (2);
INSERT INTO schedule (pothole_id) VALUES (3);
INSERT INTO schedule (pothole_id) VALUES (4);
        

