DROP TABLE SetGroup;
DROP TABLE DrillGroup;
DROP TABLE ExerciseGroup;
DROP TABLE DrillBase;
DROP TABLE DrillMuscleGroup;
DROP TABLE AuthenticationData;
DROP TABLE ClientPublicData;
DROP TABLE ClientPersonalData;
DROP TABLE TrainerData;
DROP TABLE RegistrationData;

CREATE TABLE TrainerData
(
    trainerId BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstName varchar(30)  NOT NULL,
    lastName  varchar(40)  NOT NULL,
    photoPath varchar(255) NOT NULL,
    PRIMARY KEY (trainerId)
);

INSERT INTO TrainerData (firstName, lastName, photoPath)
VALUES ('Павел', 'Кислюк', '../avatars/1ПавелКислюк123.jpg');

CREATE TABLE RegistrationData
(
    firstName       varchar(30)    NOT NULL,
    lastName        varchar(40)    NOT NULL,
    eMail           varchar(255)   NOT NULL,
    password        varchar(32672) NOT NULL,
    registrationKey varchar(30)    NOT NULL
);

CREATE TABLE ClientPersonalData
(
    clientId  BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY,
    firstName varchar(30)  NOT NULL,
    lastName  varchar(40)  NOT NULL,
    photoPath varchar(255) NOT NULL,
    PRIMARY KEY (clientId)
);

INSERT INTO ClientPersonalData (firstName, lastName, photoPath)
VALUES ('<script>alert("1");</script>', 'Kisliuk', '../avatars/1PavelKisliuk123.jpg'),
       ('Роман', 'Жминько', '../avatars/2РоманЖминько123.jpg'),
       ('Ольга', 'Безрукова', '../avatars/3ОльгаБезрукова123.jpg'),
       ('Новый', 'Клиент', '../avatars/default.jpg');

CREATE TABLE ClientPublicData
(
    clientId                BIGINT         NOT NULL,
    unavailableTrainerGroup varchar(32672) NOT NULL,
    exerciseRequest         BOOLEAN        NOT NULL,
    expiredDay              BIGINT         NOT NULL,
    restVisitation          INT            NOT NULL,
    trainerId               BIGINT,
    FOREIGN KEY (clientId) REFERENCES ClientPersonalData (clientId),
    FOREIGN KEY (trainerId) REFERENCES TrainerData (trainerId)
);

INSERT INTO ClientPublicData (clientId, unavailableTrainerGroup, exerciseRequest, expiredDay, restVisitation, trainerId)
VALUES (1, ',', true, 1567900800000, -1, 1),
       (2, ',', true, 1567900800000, 2, 1),
       (3, ',', false, 1565049600000, 4, 1),
       (4, ',', false, -1, 0, null);

CREATE TABLE AuthenticationData
(
    trainerId BIGINT,
    clientId  BIGINT,
    eMail     varchar(255)   NOT NULL,
    password  varchar(32672) NOT NULL,
    FOREIGN KEY (clientId) REFERENCES ClientPersonalData (clientId),
    FOREIGN KEY (trainerId) REFERENCES TrainerData (trainerId)
);

INSERT INTO AuthenticationData (trainerId, clientId, eMail, password)
VALUES (1, null, 'pavel_trainer@mail.ru',
        '$s0$10505$TV09w5Yp6UApmz3k9X63SQ==$hk780S7nd088FUH5Hghn2rfdgjVhKFLwHRftC96fcxc='),
       (null, 1, 'pavel_client@mail.ru',
        '$s0$10505$TV09w5Yp6UApmz3k9X63SQ==$hk780S7nd088FUH5Hghn2rfdgjVhKFLwHRftC96fcxc='),
       (null, 2, 'roman_client@mail.ru',
        '$s0$10505$TV09w5Yp6UApmz3k9X63SQ==$hk780S7nd088FUH5Hghn2rfdgjVhKFLwHRftC96fcxc='),
       (null, 3, 'olga_client@mail.ru',
        '$s0$10505$TV09w5Yp6UApmz3k9X63SQ==$hk780S7nd088FUH5Hghn2rfdgjVhKFLwHRftC96fcxc='),
       (null, 4, 'newbie_client@mail.ru',
        '$s0$10505$TV09w5Yp6UApmz3k9X63SQ==$hk780S7nd088FUH5Hghn2rfdgjVhKFLwHRftC96fcxc=');

CREATE TABLE DrillMuscleGroup
(
    muscleGroupId   BIGINT      NOT NULL,
    muscleGroupName varchar(16) NOT NULL,
    PRIMARY KEY (muscleGroupId)
);

INSERT INTO DrillMuscleGroup (muscleGroupId, muscleGroupName)
VALUES (1, 'Breast'),
       (2, 'Back'),
       (3, 'Shoulders'),
       (4, 'Biceps'),
       (5, 'Triceps'),
       (6, 'Legs'),
       (7, 'Stomach');

CREATE TABLE DrillBase
(
    drillBaseId   BIGINT         NOT NULL GENERATED ALWAYS AS IDENTITY,
    muscleGroupId BIGINT         NOT NULL,
    drillName     varchar(32672) NOT NULL,
    trainerId     BIGINT         NOT NULL,
    PRIMARY KEY (drillBaseId),
    FOREIGN KEY (muscleGroupId) REFERENCES DrillMuscleGroup (muscleGroupId),
    FOREIGN KEY (trainerId) REFERENCES TrainerData (trainerId)
);

INSERT INTO DrillBase (muscleGroupId, trainerId, drillName)
VALUES (1, 1, 'Жим штанги лёжа'),
       (1, 1, 'Бабочка'),
       (1, 1, 'Жим штанги лёжа на наклонной скамье в Смитте(угол 30°)'),
       (1, 1, 'Жим в Хаммере лёжа'),
       (1, 1, 'Разводка гантелей на наклонной скамье(угол 30°)'),
       (2, 1, 'Верхняя тяга за голову'),
       (2, 1, 'Тяга Т-грифа'),
       (2, 1, 'Верхняя тяга узким хватом к груди'),
       (2, 1, 'Нижняя тяга на тренажёре'),
       (2, 1, 'Тяга гантели в наклоне'),
       (3, 1, 'Сведение-равзведение рук с гантелями в стороны'),
       (3, 1, 'Жим в Хаммере сидя'),
       (3, 1, 'Жим в Смитте из-за головы'),
       (3, 1, 'Протяжка до подбородка узким хватом'),
       (3, 1, 'Протяжка до подбородка широким хватом'),
       (4, 1, 'Сгибание-разгибание рук с классическим грифом'),
       (4, 1, 'Сгибание-разгибание рук с S-образным грифом(хват широкий)'),
       (4, 1, 'Сгибание-разгибание рук с S-образным грифом(хват узкий)'),
       (4, 1, 'Сгибание-разгибание руки с упором на колено'),
       (4, 1, 'Сгибание-разгибание рук пронированным хватом'),
       (5, 1, 'Жим лёжа узким хватом'),
       (5, 1, 'Французский жим лёжа широким хватом'),
       (5, 1, 'Французский жим лёжа узким хватом'),
       (5, 1, 'Трицепс на блочном тренажёре с цепью'),
       (5, 1, 'Трицепс на блочном тренажёре с балкой'),
       (6, 1, 'Приседания со штангой в Смитте'),
       (6, 1, 'Приседания в "ножницах"'),
       (6, 1, 'Бицепс бедра'),
       (6, 1, 'Тренажёр на икроножные мышцы сидя'),
       (6, 1, 'Квадрицепсы на тренажёре'),
       (7, 1, 'Пресс на блочном тренажёре'),
       (7, 1, 'Римский стул'),
       (7, 1, 'Скручивания');

CREATE TABLE ExerciseGroup
(
    exerciseId     BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    exerciseNumber INT,
    exerciseDate   BIGINT,
    clientId       BIGINT NOT NULL,
    PRIMARY KEY (exerciseId),
    FOREIGN KEY (clientId) REFERENCES ClientPersonalData (clientId)
);

INSERT INTO ExerciseGroup (exerciseNumber, exerciseDate, clientId)
VALUES (1, null, 1),
       (1, null, 2);

CREATE TABLE DrillGroup
(
    drillId       BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    drillNumber   INT    NOT NULL,
    exerciseId    BIGINT NOT NULL,
    drillBaseId   BIGINT NOT NULL,
    muscleGroupId BIGINT NOT NULL,
    PRIMARY KEY (drillId),
    FOREIGN KEY (exerciseId) REFERENCES ExerciseGroup (exerciseId),
    FOREIGN KEY (drillBaseId) REFERENCES DrillBase (drillBaseId),
    FOREIGN KEY (muscleGroupId) REFERENCES DrillMuscleGroup (muscleGroupId)
);

CREATE TABLE SetGroup
(
    setId          BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
    setNumber      INT    NOT NULL,
    necessaryReps  INT    NOT NULL,
    selfConsistent INT,
    helpConsistent INT,
    weightTool     DOUBLE NOT NULL,
    restTime       INT    NOT NULL,
    exerciseId     BIGINT NOT NULL,
    drillId        BIGINT NOT NULL,
    PRIMARY KEY (setId),
    FOREIGN KEY (exerciseId) REFERENCES ExerciseGroup (exerciseId),
    FOREIGN KEY (drillId) REFERENCES DrillGroup (drillId)
);