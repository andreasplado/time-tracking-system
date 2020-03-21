DROP TABLE Job;

CREATE TABLE Job (
  id INTEGER PRIMARY KEY,
  title VARCHAR(255),
  email  VARCHAR(255),
  end_time TIMESTAMP,
  start_tme TIMESTAMP,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);