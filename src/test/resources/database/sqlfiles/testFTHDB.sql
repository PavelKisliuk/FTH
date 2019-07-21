DROP TABLE DrillSets;
DROP TABLE Drills;
DROP TABLE Exercises;
DROP TABLE DrillBase;
DROP TABLE DrillMuscleGroup;
DROP TABLE AuthenticationData;
DROP TABLE ClientPublicData;
DROP TABLE ClientPersonalData;
DROP TABLE TrainerData;
DROP TABLE RegistrationData;

CREATE TABLE TrainerData (
   trainerId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   firstName varchar (30) NOT NULL,
   lastName varchar (40) NOT NULL,
   photoPath varchar (255) NOT NULL,
   PRIMARY KEY (trainerId)
);

CREATE TABLE RegistrationData (
   firstName varchar (30) NOT NULL,
   lastName varchar (40) NOT NULL,
   eMail varchar(255) NOT NULL,
   password varchar (30) NOT NULL,
   registrationKey varchar (30) NOT NULL
);

CREATE TABLE ClientPersonalData (
   clientId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   firstName varchar (30) NOT NULL,
   lastName varchar (40) NOT NULL,
   photoPath varchar (255) NOT NULL,
   registrationKey varchar (30) NOT NULL,
   trainerId BIGINT NOT NULL,
   PRIMARY KEY (clientId),
   FOREIGN KEY (trainerId) REFERENCES TrainerData (trainerId)
);

CREATE TABLE ClientPublicData (
   clientId BIGINT NOT NULL,
   expiredDay DATE,
   restVisitation INT,
   FOREIGN KEY (clientId) REFERENCES ClientPersonalData (clientId)
);

CREATE TABLE AuthenticationData (
   personalId BIGINT NOT NULL,
   eMail varchar (255) NOT NULL,
   password varchar (30) NOT NULL,
   FOREIGN KEY (personalId) REFERENCES ClientPersonalData (clientId),
   FOREIGN KEY (personalId) REFERENCES TrainerData (trainerId)
);

CREATE TABLE DrillMuscleGroup (
   muscleGroupId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   muscleGroupName varchar (255) NOT NULL,
   PRIMARY KEY (muscleGroupId)
);

CREATE TABLE DrillBase (
   drillBaseId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
   muscleGroupId BIGINT NOT NULL,
   drillName varchar (255) NOT NULL,
   PRIMARY KEY (drillBaseId),
   FOREIGN KEY (muscleGroupId) REFERENCES DrillMuscleGroup (muscleGroupId)
);

CREATE TABLE Exercises (
    exerciseId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    exerciseNumber INT NOT NULL,
    exerciseDate DATE,
    muscleGroupId BIGINT NOT NULL,
    clientId BIGINT NOT NULL,
    PRIMARY KEY (exerciseId),
    FOREIGN KEY (muscleGroupId) REFERENCES DrillMuscleGroup (muscleGroupId),
    FOREIGN KEY (clientId) REFERENCES ClientPersonalData (clientId)
);

CREATE TABLE Drills (
    drillId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    drillNumber INT NOT NULL,
    exerciseId BIGINT NOT NULL,
    drillBaseId BIGINT NOT NULL,
    PRIMARY KEY (drillId),
    FOREIGN KEY (exerciseId) REFERENCES Exercises (exerciseId),
    FOREIGN KEY (drillBaseId) REFERENCES DrillBase (drillBaseId)
);

CREATE TABLE DrillSets (
    setId BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    setNumber INT NOT NULL,
    necessaryReps INT NOT NULL,
    selfConsistent INT NOT NULL,
    helpConsistent INT NOT NULL,
    weightTool FLOAT NOT NULL,
    restTime TIME,
    exerciseId BIGINT NOT NULL,
    drillId BIGINT NOT NULL,
    FOREIGN KEY (exerciseId) REFERENCES Exercises (exerciseId),
    FOREIGN KEY (drillId) REFERENCES Drills (drillId)
);

INSERT INTO TrainerData (firstName, lastName, photoPath)
VALUES
   ('Павел','Кислюк', 'https://pp.userapi.com/c637229/v637229874/21fa5/v6r_UTH_uCw.jpg');

INSERT INTO ClientPersonalData (firstName, lastName, photoPath, registrationKey, trainerId)
VALUES
   ('Pavel','Kisliuk', 'https://pp.userapi.com/c638330/v638330874/419bb/AzS3PYR_kf0.jpg', '-', 1);

INSERT INTO ClientPublicData (clientId, expiredDay, restVisitation)
VALUES
   (1,'2019-09-08', -1);

INSERT INTO AuthenticationData (personalId, eMail, password)
VALUES
   (1,'pavelsergeevichkisliuk2015@mail.ru', '210194'),
   (1, 'pavel-2008.94@mail.ru', '210194');

INSERT INTO DrillMuscleGroup (muscleGroupName)
VALUES
   ('Breast'),
   ('Back'),
   ('Shoulders'),
   ('Biceps'),
   ('Triceps'),
   ('Legs'),
   ('Stomach');

INSERT INTO DrillBase (muscleGroupId, drillName)
VALUES
   (1,'Bench press'),
   (1,'Pectoral machine (Butterfly)'),
   (1,'Incline bench press (30*)'),
   (1,'Hammer strength flat bench press');

INSERT INTO Exercises (exerciseNumber, exerciseDate, muscleGroupId, clientId)
VALUES
   (1,'2019-06-21', 1, 1);

INSERT INTO Drills (drillNumber, exerciseId, drillBaseId)
VALUES
   (1, 1, 1),
   (2, 1, 2),
   (3, 1, 3),
   (4, 1, 4);

INSERT INTO DrillSets (setNumber, necessaryReps, selfConsistent, helpConsistent, weightTool, restTime, exerciseId, drillId)
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