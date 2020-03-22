DROP TABLE studentclass;
ALTER TABLE Job DROP COLUMN starttime;
ALTER TABLE Job DROP COLUMN endtime;
ALTER TABLE Class DROP COLUMN starttime;
ALTER TABLE Class DROP COLUMN endtime;
ALTER TABLE Class DROP COLUMN timeminutes;
ALTER TABLE Class DROP COLUMN teachername;
DROP TABLE student_classes_with_clashes;