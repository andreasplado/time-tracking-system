CREATE TABLE Users(
  id INTEGER PRIMARY KEY,
  username varchar(50) NOT NULL,
  password varchar(65) NOT NULL,
  role varchar(15) NOT NULL);

CREATE TABLE UserProfile(
  id INTEGER PRIMARY KEY,
  fullname VARCHAR(255),
  email VARCHAR(255),
  idCode VARCHAR(255),
  username VARCHAR(255),
  phone VARCHAR(255),
  startTime TIMESTAMP,
  endTime TIMESTAMP
);

CREATE TABLE WorkHour (
  id INTEGER PRIMARY KEY,
  title VARCHAR(255),
  username  VARCHAR(255),
  end_time  VARCHAR(255),
  start_tme  VARCHAR(255),
  created_at  DATE NOT NULL DEFAULT CURRENT_DATE,
  updated_at  DATE
);
