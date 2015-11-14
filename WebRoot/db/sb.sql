PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE students(
userID text primary key not null,
userName text not null,
userPasswd text not null,
userAuth int not null,
userEmail text UNIQUE);
INSERT INTO "students" VALUES('U201417138','leonardo','passwd',1,'qqq@qq.com');
INSERT INTO "students" VALUES('U201417137','Stephen Pile
','passwd',1,'qq@qq.com');
INSERT INTO "students" VALUES('U201417136','Abraham Lincoln','asdf',1,'null');
CREATE TABLE bulletins(
bulletID integer primary key autoincrement not null,
bulletMsg text not null,
bulletTime text not null,
userID text not null,
foreign key(userID) references students(userID));
INSERT INTO "bulletins" VALUES(12,'Never Go Die !','11.12','U201417138');
INSERT INTO "bulletins" VALUES(19,'Life may have no meaning, or, even worse, it may have a meaning of which
you disapprove.','11.13','U201417137');
INSERT INTO "bulletins" VALUES(20,'I don''t know who my grandfather was; I am much more concerned to know
what his grandson will be.','11.13','U201417136');
CREATE TABLE comments(
commentID integer primary key autoincrement not null,
commentMsg text not null,
commentTime text not null,
userID text not null,
bulletID integer not null,
foreign key(userID) references students(userID),
foreign key(bulletID) references bulletins(bulletID)
);
CREATE TABLE favors(
favorID integer primary key autoincrement not null,
favorTime text not null,
userID text not null,
bulletID integer not null,
foreign key(userID) references students(userID),
foreign key(bulletID) references bulletins(bulletID)
);
INSERT INTO "favors" VALUES(1,'11.12','U201417137',12);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('bulletins',20);
INSERT INTO "sqlite_sequence" VALUES('comments',5);
INSERT INTO "sqlite_sequence" VALUES('favors',13);
COMMIT;
