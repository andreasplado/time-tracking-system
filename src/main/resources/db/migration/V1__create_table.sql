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

