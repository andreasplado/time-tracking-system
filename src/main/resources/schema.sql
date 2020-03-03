DROP TABLE IF EXISTS class_schedule;
DROP TABLE IF EXISTS student_class;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS job;

CREATE TABLE Student (
  id INTEGER PRIMARY KEY,
  name VARCHAR(50),
  email  VARCHAR(50)
);

CREATE TABLE Class (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30),
  description VARCHAR(300),
  teacher_name VARCHAR(50),
  time_minutes INTEGER

);

CREATE TABLE Job(
  id INTEGER PRIMARY KEY,
  title VARCHAR(255),
  start_time TIMESTAMP,
  end_time TIMESTAMP

)

CREATE TABLE Class_schedule (
  id INTEGER PRIMARY KEY,
  class_id INTEGER FOREIGN KEY REFERENCES class(id) ON DELETE CASCADE,
  start_time TIMESTAMP,
);

CREATE TABLE Student_class (
  id INTEGER PRIMARY KEY,
  student_id INTEGER REFERENCES student(id),
  class_id INTEGER REFERENCES class(id) ON DELETE CASCADE,
);