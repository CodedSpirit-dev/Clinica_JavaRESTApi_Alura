-- Table: consultations
CREATE TABLE consultations (
                               id BIGINT NOT NULL AUTO_INCREMENT,
                               medico_id BIGINT,
                               pacient_id BIGINT,
                               date_time TIMESTAMP,
                               PRIMARY KEY (id),
                               CONSTRAINT fk_medico FOREIGN KEY (medico_id) REFERENCES medics(id),
                               CONSTRAINT fk_pacient FOREIGN KEY (pacient_id) REFERENCES pacients(id)
);

-- Table: pacients
CREATE TABLE pacients (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          phone_number VARCHAR(20) NOT NULL,
                          document VARCHAR(50) NOT NULL UNIQUE,
                          street VARCHAR(100) NOT NULL,
                          district VARCHAR(100) NOT NULL,
                          city VARCHAR(100) NOT NULL,
                          number VARCHAR(10) NOT NULL,
                          complement VARCHAR(100),
                          active BOOLEAN NOT NULL,
                          PRIMARY KEY (id)
);