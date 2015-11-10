PRAGMA foreign_keys=ON;
BEGIN TRANSACTION;
CREATE TABLE students(
userID text primary key not null,
userName text not null,
userPasswd text not null,
userAuth int not null,
userEmail text);
INSERT INTO "students" VALUES('U201417138','LiChenda','123',0,'leo@leo.com');
CREATE TABLE bulletins(
bulletID integer primary key autoincrement not null,
bulletMsg text not null,
bulletTime text not null,
userID text not null,
foreign key(userID) references students(userID));
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
INSERT INTO "bulletins" VALUES(1,'test1','today 3:33','U201417138');
INSERT INTO "bulletins" VALUES(2,'test2','today 3.34','U201417138');
INSERT INTO "comments" VALUES(1,'comments1','2:22','U201417138',1);
COMMIT;