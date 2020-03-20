ALTER TABLE Users ADD COLUMN id_code VARCHAR;

CREATE TABLE UserDetails(
  id INTEGER PRIMARY KEY,
  fullname VARCHAR(255),
  email VARCHAR(255),
  idCode VARCHAR(255),
  username VARCHAR(255),
  phone VARCHAR(255),
  startTime TIMESTAMP,
  endTime TIMESTAMP
);