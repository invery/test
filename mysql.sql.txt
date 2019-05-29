USE test;

DROP TABLE IF EXISTS comps;

CREATE TABLE comps
(
    id int(10) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    isNeeded BIT DEFAULT false  NOT NULL,
    quantity int(4) NOT NULL
)
    COLLATE='utf8_general_ci';
CREATE UNIQUE INDEX films_title_uindex ON comps (id);

INSERT INTO `comps` (`name`,`isNeeded`, quantity)
VALUES
("Корпус", 0, 10),
("Материнская плата", 1, 5),
("Процессор", 1, 10),
("Оперативная память", 1, 10),
("Видеокарта", 0, 10),
("Блок питания", 1, 10),
("Жесткий диск", 1, 10),
("Провод1", 0, 10),
("Провод2", 0, 10),
("Провод3", 0, 10),
("Провод4", 0, 10),
("Провод5", 0, 10),
("Провод6", 0, 10),
("Провод7", 0, 10),
("Провод8", 0, 10),
("Провод9", 0, 10),
("Провод10", 0, 10),
("Провод11", 0, 10),
("Провод12", 0, 10),
("Провод13", 0, 10),
("Провод14", 0, 10);
