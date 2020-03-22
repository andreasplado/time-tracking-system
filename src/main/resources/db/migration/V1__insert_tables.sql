CREATE TABLE Users(
  id INTEGER PRIMARY KEY,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(65) NOT NULL,
  role VARCHAR(15) NOT NULL);

CREATE TABLE UserProfile(
  id INTEGER PRIMARY KEY,
  fullname VARCHAR(255),
  email VARCHAR(255),
  idCode VARCHAR(255),
  username VARCHAR(255),
  phone VARCHAR(255)
);

CREATE TABLE WorkHour (
  id INTEGER PRIMARY KEY,
  title VARCHAR(255),
  username VARCHAR(255),
  end_time VARCHAR(255),
  start_tme VARCHAR(255),
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE Student (
  id INTEGER PRIMARY KEY,
  name VARCHAR(50),
  email  VARCHAR(50)
);

CREATE TABLE Class (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  description VARCHAR(300),
  teacherName VARCHAR(50),
  timeMinutes INTEGER,
  startTime TIMESTAMP,
  endTime TIMESTAMP
);

CREATE TABLE ClassSchedule (
  id INTEGER PRIMARY KEY,
  classId INTEGER REFERENCES Class(id) ON DELETE CASCADE,
  startTime TIMESTAMP
);

CREATE TABLE StudentClass (
  id INTEGER PRIMARY KEY,
  studentId INTEGER REFERENCES Student(id),
  classId INTEGER REFERENCES Class(id) ON DELETE CASCADE
);

