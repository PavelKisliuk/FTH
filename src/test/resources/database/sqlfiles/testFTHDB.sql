DROP TABLE DrillSets;
DROP TABLE Drills;
DROP TABLE Exercises;
DROP TABLE DrillBase;
DROP TABLE DrillMuscleGroup;
DROP TABLE ClientAuthenticationData;
DROP TABLE ClientPublicData;
DROP TABLE ClientPersonalData;
DROP TABLE RegistrationData;

CREATE TABLE RegistrationData (
   firstName varchar (30) NOT NULL,
   lastName varchar (40) NOT NULL,
   eMail varchar(255) NOT NULL,
   password varchar (30) NOT NULL,
   registrationKey varchar (30) NOT NULL
);

CREATE TABLE ClientPersonalData (
   clientID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   firstName varchar (30) NOT NULL,
   lastName varchar (40) NOT NULL,
   photoPath varchar (255) NOT NULL,
   registrationKey varchar (30) NOT NULL,
   PRIMARY KEY (clientID)
);

CREATE TABLE ClientPublicData (
   clientID BIGINT NOT NULL,
   expiredDay DATE,
   restVisitation INT,
   FOREIGN KEY (clientID) REFERENCES ClientPersonalData (clientID)
);

CREATE TABLE ClientAuthenticationData (
   clientID BIGINT NOT NULL,
   clientEmail varchar (255) NOT NULL,
   clientPassword varchar (30) NOT NULL,
   FOREIGN KEY (clientID) REFERENCES ClientPersonalData (clientID)
);

CREATE TABLE DrillMuscleGroup (
   muscleGroupID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   muscleGroupName varchar (255) NOT NULL,
   PRIMARY KEY (muscleGroupID)
);

CREATE TABLE DrillBase (
   drillBaseID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   muscleGroupID BIGINT NOT NULL,
   drillName varchar (255) NOT NULL,
   PRIMARY KEY (drillBaseID),
   FOREIGN KEY (muscleGroupID) REFERENCES DrillMuscleGroup (muscleGroupID)
);

CREATE TABLE Exercises (
    exerciseID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    exerciseNumber INT NOT NULL,
    exerciseDate DATE,
    muscleGroupID BIGINT NOT NULL,
    clientID BIGINT NOT NULL,
    PRIMARY KEY (exerciseID),
    FOREIGN KEY (muscleGroupID) REFERENCES DrillMuscleGroup (muscleGroupID),
    FOREIGN KEY (clientID) REFERENCES ClientPersonalData (clientID)
);

CREATE TABLE Drills (
    drillID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    drillNumber INT NOT NULL,
    exerciseID BIGINT NOT NULL,
    drillBaseID BIGINT NOT NULL,
    PRIMARY KEY (drillID),
    FOREIGN KEY (exerciseID) REFERENCES Exercises (exerciseID),
    FOREIGN KEY (drillBaseID) REFERENCES DrillBase (drillBaseID)
);

CREATE TABLE DrillSets (
    setID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    setNumber INT NOT NULL,
    necessaryReps INT NOT NULL,
    selfConsistent INT NOT NULL,
    helpConsistent INT NOT NULL,
    weightTool FLOAT NOT NULL,
    restTime TIME,
    exerciseID BIGINT NOT NULL,
    drillID BIGINT NOT NULL,
    FOREIGN KEY (exerciseID) REFERENCES Exercises (exerciseID),
    FOREIGN KEY (drillID) REFERENCES Drills (drillID)
);

INSERT INTO ClientPersonalData (firstName, lastName, photoPath, registrationKey)
VALUES
   ('Pavel','Kisliuk', 'testPhoto/testPhoto.jpg', '-');

INSERT INTO ClientPublicData (clientID, expiredDay, restVisitation)
VALUES
   (1,'2019-09-08', -1);

INSERT INTO ClientAuthenticationData (clientID, clientEmail, clientPassword)
VALUES
   (1,'pavelsergeevichkisliuk2015@mail.ru', '210194');

INSERT INTO DrillMuscleGroup (muscleGroupName)
VALUES
   ('Breast'),
   ('Back'),
   ('Shoulders'),
   ('Biceps'),
   ('Triceps'),
   ('Legs'),
   ('Stomach');

INSERT INTO DrillBase (muscleGroupID, drillName)
VALUES
   (1,'Bench press'),
   (1,'Pectoral machine (Butterfly)'),
   (1,'Incline bench press (30*)'),
   (1,'Hammer strength flat bench press');

INSERT INTO Exercises (exerciseNumber, exerciseDate, muscleGroupID, clientID)
VALUES
   (1,'2019-06-21', 1, 1);

INSERT INTO Drills (drillNumber, exerciseID, drillBaseID)
VALUES
   (1, 1, 1),
   (2, 1, 2),
   (3, 1, 3),
   (4, 1, 4);

INSERT INTO DrillSets (setNumber, necessaryReps, selfConsistent, helpConsistent, weightTool, restTime, exerciseID, drillID)
VALUES
   (1, 10, 9, 1, 100, '00:01:20', 1, 1),
   (2, 10, 8, 2, 95, '00:01:20', 1, 1),
   (3, 10, 7, 2, 90, '00:01:20', 1, 1),
   (4, 10, 7, 2, 85, '00:01:20', 1, 1),
   (1, 12, 12, 0, 45, '00:01:00', 1, 2),
   (2, 12, 10, 0, 40, '00:01:00', 1, 2),
   (3, 12, 9, 0, 35, '00:01:00', 1, 2),
   (4, 12, 8, 0, 30, '00:01:00', 1, 2),
   (1, 8, 8, 0, 70, '00:01:00', 1, 3),
   (2, 8, 7, 1, 65, '00:01:00', 1, 3),
   (3, 8, 7, 1, 60, '00:01:00', 1, 3),
   (4, 8, 6, 1, 55, '00:01:00', 1, 3),
   (1, 12, 12, 0, 60, '00:01:00', 1, 4),
   (2, 12, 10, 0, 55, '00:01:00', 1, 4),
   (3, 12, 10, 0, 50, '00:01:00', 1, 4),
   (4, 12, 9, 0, 45, '00:01:00', 1, 4);