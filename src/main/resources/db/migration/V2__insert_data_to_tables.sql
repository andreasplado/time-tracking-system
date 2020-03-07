DROP TABLE IF EXISTS ClassSchedule;
DROP TABLE IF EXISTS StudentClass;
DROP TABLE IF EXISTS Student;
DROP TABLE IF EXISTS Class;
DROP TABLE IF EXISTS Job;


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

CREATE TABLE Job(
  id INTEGER PRIMARY KEY,
  title VARCHAR(255),
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

INSERT INTO Class VALUES (1,'Füüsika I', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed congue molestie risus, in imperdiet urna consectetur et.', 'Kalle Mannik', '60');
INSERT INTO Class VALUES (2,'Füüsika II', 'Proin sed bibendum felis. Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'Viktor Malk', '90');
INSERT INTO Class VALUES (3,'Matemaatiline analüüs', 'Quisque eleifend vulputate tempus. Nunc orci nisl, vulputate in mauris eget, lacinia iaculis arcu. Ut eu volutpat neque.', 'Endrik Kangur', '30');
INSERT INTO Class VALUES (4,'Lineaaralgebra', 'Maecenas ac sagittis lacus. Donec vehicula tincidunt eros id fringilla.', 'Kaspar Laas', '60');
INSERT INTO Class VALUES (5,'Psühholoogia', 'Nulla egestas mauris id interdum volutpat. Aliquam et gravida augue. ', 'Juri Parn', '60');
INSERT INTO Class VALUES (6,'Filosoofia', 'Aenean dui est, pulvinar nec laoreet sit amet, posuere at ante. ', 'Enn Laas', '60');
INSERT INTO Class VALUES (7,'Geograafia I', 'Phasellus fermentum pharetra metus, ac ultrices libero ornare ac.', 'Riho Sisask', '120');
INSERT INTO Class VALUES (8,'Geograafia II', 'Praesent dapibus dui vitae massa fringilla molestie. Sed sed elit ut nisl pellentesque blandit nec luctus erat.', 'Albert Kangro', '180');
INSERT INTO Class VALUES (9,'Inglise keel', 'Aliquam erat volutpat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ', 'Anti Paasuke', '60');
INSERT INTO Class VALUES (10,'Vene keel', 'Aliquam consequat rhoncus eleifend. Fusce sagittis at sem eget maximus. Pellentesque vel interdum ex.', 'Gustav Kuusk', '60');
INSERT INTO Class VALUES (11,'Õigusteadus', 'Praesent facilisis, ex vel hendrerit consequat, massa dui tempus diam, quis tempor enim eros nec mauris.', 'Eliisa Puusepp', '60');
INSERT INTO Class VALUES (12,'Keemia', 'Integer viverra molestie aliquet. Sed lacus libero, posuere tempor feugiat ut, suscipit eget est.', 'Kaili Levandi', '30');
INSERT INTO Class VALUES (13,'Veebirakendused', 'Phasellus a elit sit amet sapien efficitur tristique vitae at dolor.', 'Karolin Kask', '90');
INSERT INTO Class VALUES (14,'Kirjandus', 'Proin euismod cursus dui, sed aliquet urna iaculis id.', 'Hedi Nurmsalu', '60');
INSERT INTO Class VALUES (15,'Elektroonika ', 'Ut pellentesque, elit volutpat ultricies efficitur, odio ligula congue odio, nec rutrum nisl velit a nisl.', 'Taimi Keskula', '120');
INSERT INTO Class VALUES (16,'Geoloogia', 'Maecenas vel convallis elit, quis hendrerit nibh. Phasellus tincidunt vehicula nulla, non finibus arcu egestas vitae. ', 'Vilja Eenpalu', '120');
INSERT INTO Class VALUES (17,'Majandus', 'Suspendisse bibendum cursus facilisis. Cras commodo erat mi.', 'Lembi Jogi', '60');
INSERT INTO Class VALUES (18,'Arvutivõrgud', 'Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. In tempus tortor erat.', 'Merike Mark', '90');
INSERT INTO Class VALUES (19,'Programmeerimine I', 'Maecenas odio augue, bibendum ac malesuada quis, ullamcorper nec velit. ', 'Anu Kapp', '60');
INSERT INTO Class VALUES (20,'Programmeerimine II', 'Nullam elementum sapien et congue condimentum.', 'Elis Kirsipuu', '60');


INSERT INTO ClassSchedule VALUES (2,'2', '2017-11-21 10:00:00');
INSERT INTO ClassSchedule VALUES (3,'9', '2017-11-22 10:00:00');
INSERT INTO ClassSchedule VALUES (4,'5', '2017-11-23 10:00:00');
INSERT INTO ClassSchedule VALUES (5,'8', '2017-11-24 10:00:00');
INSERT INTO ClassSchedule VALUES (6,'4', '2017-11-20 12:00:00');
INSERT INTO ClassSchedule VALUES (7,'11', '2017-11-21 12:00:00');
INSERT INTO ClassSchedule VALUES (8,'3', '2017-11-22 12:00:00');
INSERT INTO ClassSchedule VALUES (9,'16', '2017-11-23 12:00:00');
INSERT INTO ClassSchedule VALUES (10,'8', '2017-11-24 12:00:00');
INSERT INTO ClassSchedule VALUES (11,'17', '2017-11-20 14:00:00');
INSERT INTO ClassSchedule VALUES (12,'5', '2017-11-21 14:00:00');
INSERT INTO ClassSchedule VALUES (13,'10', '2017-11-22 14:00:00');
INSERT INTO ClassSchedule VALUES (14,'12', '2017-11-23 14:00:00');
INSERT INTO ClassSchedule VALUES (15,'7', '2017-11-24 14:00:00');
INSERT INTO ClassSchedule VALUES (16,'13', '2017-11-20 16:00:00');
INSERT INTO ClassSchedule VALUES (17,'6', '2017-11-21 16:00:00');
INSERT INTO ClassSchedule VALUES (18,'18', '2017-11-22 16:00:00');
INSERT INTO ClassSchedule VALUES (19,'14', '2017-11-23 16:00:00');
INSERT INTO ClassSchedule VALUES (20,'1', '2017-11-24 16:00:00');
INSERT INTO ClassSchedule VALUES (21,'13', '2017-11-20 18:00:00');
INSERT INTO ClassSchedule VALUES (22,'9', '2017-11-21 18:00:00');
INSERT INTO ClassSchedule VALUES (23,'19', '2017-11-22 18:00:00');
INSERT INTO ClassSchedule VALUES (24,'6', '2017-11-23 18:00:00');
INSERT INTO ClassSchedule VALUES (25,'15', '2017-11-24 18:00:00');
INSERT INTO ClassSchedule VALUES (26,'2', '2017-11-20 13:00:00');
INSERT INTO ClassSchedule VALUES (27,'9', '2017-11-21 13:00:00');
INSERT INTO ClassSchedule VALUES (28,'10', '2017-11-22 13:00:00');
INSERT INTO ClassSchedule VALUES (29,'3', '2017-11-23 13:00:00');
INSERT INTO ClassSchedule VALUES (30,'20', '2017-11-24 13:00:00');
INSERT INTO ClassSchedule VALUES (31,'20', '2017-11-24 16:00:00');
INSERT INTO ClassSchedule VALUES (32,'15', '2017-11-21 16:00:00');
INSERT INTO ClassSchedule VALUES (33,'13', '2017-11-22 10:00:00');
INSERT INTO ClassSchedule VALUES (34,'16', '2017-11-21 12:00:00');
INSERT INTO ClassSchedule VALUES (35,'3', '2017-11-21 14:00:00');
INSERT INTO ClassSchedule VALUES (36,'17', '2017-11-20 10:00:00');
INSERT INTO ClassSchedule VALUES (37,'15', '2017-11-21 10:00:00');


INSERT INTO Job VALUES (2, 'Akende pesemine', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (3, 'Põranda puhastus', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (4, 'Lehtede riisumine', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (5, 'Metallitööstus', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (6, 'Nõude pesemine', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (7, 'Tehnoülevaatlus', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (8, 'Roogitav räim', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (9, 'Ilusasti puhas', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (10, 'Test', '2017-11-21 14:00:00', '2017-12-21 14:00:00');
INSERT INTO Job VALUES (11, 'Hahaha', '2017-11-21 14:00:00', '2017-12-21 14:00:00');


INSERT INTO Student VALUES (1,'Jaakob Soosaar', 'jaakob.soosaar@gmail.com');
INSERT INTO Student VALUES (2,'Riho Ruutli', 'riho.ruutli@gmail.com');
INSERT INTO Student VALUES (3,'Laur Peebo', 'laur.peebo@gmail.com');
INSERT INTO Student VALUES (4,'Siimeon Kukk', 'siimeon.kukk@gmail.com');
INSERT INTO Student VALUES (5,'Tuudor Lippmaa', 'tuudor.lippmaa@gmail.com');
INSERT INTO Student VALUES (6,'Taivo Puhvel', 'taivo.puhvel@gmail.com');
INSERT INTO Student VALUES (7,'Siimon Lepmets', 'siimon.lepmets@gmail.com');
INSERT INTO Student VALUES (8,'Timmo Kapp', 'timmo.kapp@gmail.com');
INSERT INTO Student VALUES (9,'Kardo Kirsipuu', 'kardo.kirsipuu@gmail.com');
INSERT INTO Student VALUES (10,'Martin Eenpalu', 'martin.eenpalu@gmail.com');
INSERT INTO Student VALUES (11,'Hedi Aavik', 'hedi.aavik@gmail.com');
INSERT INTO Student VALUES (12,'Karin Part', 'karin.part@gmail.com');
INSERT INTO Student VALUES (13,'Kristiina Sokk', 'kristiina.sokk@gmail.com');
INSERT INTO Student VALUES (14,'Kaarin Valbe', 'kaarin.valbe@gmail.com');
INSERT INTO Student VALUES (15,'Rita Ruutli', 'rita.ruutli@gmail.com');
INSERT INTO Student VALUES (16,'Karolin Ruutli', 'karolin.ruutli@gmail.com');
INSERT INTO Student VALUES (17,'Helga Vosu', 'helga.vosu@gmail.com');
INSERT INTO Student VALUES (18,'Kullike Pihlak', 'kullike.pihlak@gmail.com');
INSERT INTO Student VALUES (19,'Made Kalda', 'made.kalda@gmail.com');
INSERT INTO Student VALUES (20,'Rita Eenpalu', 'rita.eenpalu@gmail.com');
INSERT INTO Student VALUES (21,'Joonatan Lepmets', 'joonatan.lepmets@gmail.com');
INSERT INTO Student VALUES (22,'Oliver Kivi', 'oliver.kivi@gmail.com');
INSERT INTO Student VALUES (23,'Aksel Rebane', 'aksel.rebane@gmail.com');
INSERT INTO Student VALUES (24,'Andreas Korjus', 'andreas.korjus@gmail.com');
INSERT INTO Student VALUES (25,'Kustas Paasuke', 'kustas.paasuke@gmail.com');
INSERT INTO Student VALUES (26,'Edgar Rummo', 'edgar.rummo@gmail.com');
INSERT INTO Student VALUES (27,'Paavo Laurits', 'paavo.laurits@gmail.com');
INSERT INTO Student VALUES (28,'Toivo Leok', 'toivo.leok@gmail.com');
INSERT INTO Student VALUES (29,'Joonatan Vaher', 'joonatan.vaher@gmail.com');
INSERT INTO Student VALUES (30,'Timo Olesk', 'timo.olesk@gmail.com');
INSERT INTO Student VALUES (31,'Ilme Laht', 'ilme.laht@gmail.com');
INSERT INTO Student VALUES (32,'Karolin Kalda', 'karolin.kalda@gmail.com');
INSERT INTO Student VALUES (33,'Helina Mand', 'helina.mand@gmail.com');
INSERT INTO Student VALUES (34,'Eliisabet Koiv', 'eliisabet.koiv@gmail.com');
INSERT INTO Student VALUES (35,'Brita Ruutel', 'brita.ruutel@gmail.com');
INSERT INTO Student VALUES (36,'Elvi Kukk', 'elvi.kukk@gmail.com');
INSERT INTO Student VALUES (37,'Onne Masing', 'onne.masing@gmail.com');
INSERT INTO Student VALUES (38,'Tuuli Magi', 'tuuli.magi@gmail.com');
INSERT INTO Student VALUES (39,'Marta Kuusik', 'marta.kuusik@gmail.com');
INSERT INTO Student VALUES (40,'Ingrid Kuusik', 'ingrid.kuusik@gmail.com');
INSERT INTO Student VALUES (41,'Aile Putsep', 'aile.putsep@gmail.com');
INSERT INTO Student VALUES (42,'Rahel Eskola', 'rahel.eskola@gmail.com');
INSERT INTO Student VALUES (43,'Regina Allik', 'regina.allik@gmail.com');
INSERT INTO Student VALUES (44,'Minna Leok', 'minna.leok@gmail.com');
INSERT INTO Student VALUES (45,'Elle Kuusk', 'elle.kuusk@gmail.com');
INSERT INTO Student VALUES (46,'Liina Korjus', 'liina.korjus@gmail.com');
INSERT INTO Student VALUES (47,'Helmi Keskkula', 'helmi.keskkula@gmail.com');
INSERT INTO Student VALUES (48,'Onnela Lepp', 'onnela.lepp@gmail.com');
INSERT INTO Student VALUES (49,'Aleksandra Oja', 'aleksandra.oja@gmail.com');
INSERT INTO Student VALUES (50,'Kertu Koiv', 'kertu.koiv@gmail.com');
INSERT INTO Student VALUES (51,'Endrik Eenpalu', 'endrik.eenpalu@gmail.com');
INSERT INTO Student VALUES (52,'Madis Vitsut', 'madis.vitsut@gmail.com');
INSERT INTO Student VALUES (53,'Raigo Valjas', 'raigo.valjas@gmail.com');
INSERT INTO Student VALUES (54,'Henn Eskola', 'henn.eskola@gmail.com');
INSERT INTO Student VALUES (55,'Joonas Mand', 'joonas.mand@gmail.com');
INSERT INTO Student VALUES (56,'Aabel Keskkula', 'aabel.keskkula@gmail.com');
INSERT INTO Student VALUES (57,'Jaan Aare', 'jaan.aare@gmail.com');
INSERT INTO Student VALUES (58,'Romet Ruutel', 'romet.ruutel@gmail.com');
INSERT INTO Student VALUES (59,'Ergo Peebo', 'ergo.peebo@gmail.com');
INSERT INTO Student VALUES (60,'Vootele Kuusk', 'vootele.kuusk@gmail.com');


INSERT INTO StudentClass VALUES (1, 1, 16);
INSERT INTO StudentClass VALUES (2, 2, 17);
INSERT INTO StudentClass VALUES (3, 3, 10);
INSERT INTO StudentClass VALUES (4, 4, 1);
INSERT INTO StudentClass VALUES (5, 5, 14);
INSERT INTO StudentClass VALUES (6, 6, 13);
INSERT INTO StudentClass VALUES (7, 7, 12);
INSERT INTO StudentClass VALUES (8, 8, 13);
INSERT INTO StudentClass VALUES (9, 9, 11);
INSERT INTO StudentClass VALUES (10, 10, 2);
INSERT INTO StudentClass VALUES (11, 11, 7);
INSERT INTO StudentClass VALUES (12, 12, 16);
INSERT INTO StudentClass VALUES (13, 13, 1);
INSERT INTO StudentClass VALUES (14, 14, 9);
INSERT INTO StudentClass VALUES (15, 15, 15);
INSERT INTO StudentClass VALUES (16, 16, 20);
INSERT INTO StudentClass VALUES (17, 17, 17);
INSERT INTO StudentClass VALUES (18, 18, 11);
INSERT INTO StudentClass VALUES (19, 19, 15);
INSERT INTO StudentClass VALUES (20, 20, 1);
INSERT INTO StudentClass VALUES (21, 21, 19);
INSERT INTO StudentClass VALUES (22, 22, 3);
INSERT INTO StudentClass VALUES (23, 23, 12);
INSERT INTO StudentClass VALUES (24, 24, 11);
INSERT INTO StudentClass VALUES (25, 25, 10);
INSERT INTO StudentClass VALUES (26, 26, 7);
INSERT INTO StudentClass VALUES (27, 27, 13);
INSERT INTO StudentClass VALUES (28, 28, 15);
INSERT INTO StudentClass VALUES (29, 29, 19);
INSERT INTO StudentClass VALUES (30, 30, 3);
INSERT INTO StudentClass VALUES (31, 31, 6);
INSERT INTO StudentClass VALUES (32, 32, 6);
INSERT INTO StudentClass VALUES (33, 33, 6);
INSERT INTO StudentClass VALUES (34, 34, 4);
INSERT INTO StudentClass VALUES (35, 35, 16);
INSERT INTO StudentClass VALUES (36, 36, 13);
INSERT INTO StudentClass VALUES (37, 37, 14);
INSERT INTO StudentClass VALUES (38, 38, 4);
INSERT INTO StudentClass VALUES (39, 39, 8);
INSERT INTO StudentClass VALUES (40, 40, 1);
INSERT INTO StudentClass VALUES (41, 41, 3);
INSERT INTO StudentClass VALUES (42, 42, 12);
INSERT INTO StudentClass VALUES (43, 43, 10);
INSERT INTO StudentClass VALUES (44, 44, 17);
INSERT INTO StudentClass VALUES (45, 45, 4);
INSERT INTO StudentClass VALUES (46, 46, 12);
INSERT INTO StudentClass VALUES (47, 47, 18);
INSERT INTO StudentClass VALUES (48, 48, 18);
INSERT INTO StudentClass VALUES (49, 49, 3);
INSERT INTO StudentClass VALUES (50, 50, 11);
INSERT INTO StudentClass VALUES (51, 51, 3);
INSERT INTO StudentClass VALUES (52, 52, 4);
INSERT INTO StudentClass VALUES (53, 53, 1);
INSERT INTO StudentClass VALUES (54, 54, 1);
INSERT INTO StudentClass VALUES (55, 55, 8);
INSERT INTO StudentClass VALUES (56, 56, 11);
INSERT INTO StudentClass VALUES (57, 57, 12);
INSERT INTO StudentClass VALUES (58, 58, 7);
INSERT INTO StudentClass VALUES (59, 59, 13);
INSERT INTO StudentClass VALUES (60, 60, 9);
INSERT INTO StudentClass VALUES (61, 1, 1);
INSERT INTO StudentClass VALUES (62, 2, 12);
INSERT INTO StudentClass VALUES (63, 3, 10);
INSERT INTO StudentClass VALUES (64, 4, 13);
INSERT INTO StudentClass VALUES (65, 5, 12);
INSERT INTO StudentClass VALUES (66, 6, 19);
INSERT INTO StudentClass VALUES (67, 7, 10);
INSERT INTO StudentClass VALUES (68, 8, 9);
INSERT INTO StudentClass VALUES (69, 9, 2);
INSERT INTO StudentClass VALUES (70, 10, 8);
INSERT INTO StudentClass VALUES (71, 11, 7);
INSERT INTO StudentClass VALUES (72, 12, 16);
