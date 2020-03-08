DROP TABLE student_classes;
DROP TABLE studentclass;
ALTER TABLE Job DROP COLUMN starttime, endtime;
ALTER TABLE Class DROP COLUMN starttime, endtime, timeminutes, teachername;
DROP TABLE student_classes_with_clashes;