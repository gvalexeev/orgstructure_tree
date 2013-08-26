INSERT INTO department (name, parent_id) VALUES ('Ай-Теко', null);
INSERT INTO department (name, parent_id) VALUES ('Департамент Корпоративных Систем', (SELECT
                                                                                        id
                                                                                      FROM department
                                                                                      WHERE name = 'Ай-Теко'));
INSERT INTO department (name, parent_id) VALUES ('Департамент Финансового Благополучия', (SELECT
                                                                                            id
                                                                                          FROM department
                                                                                          WHERE name = 'Ай-Теко'));
INSERT INTO department (name, parent_id) VALUES ('Департамент Большущих Прибылей', (SELECT
                                                                                      id
                                                                                    FROM department
                                                                                    WHERE name =
                                                                                          'Департамент Финансового Благополучия'));
INSERT INTO department (name, parent_id) VALUES ('Департамент Пустых Надежд', (SELECT
                                                                                 id
                                                                               FROM department
                                                                               WHERE name =
                                                                                     'Департамент Финансового Благополучия'));
INSERT INTO department (name, parent_id) VALUES ('Дочерняя компания', null);
INSERT INTO department (name, parent_id) VALUES ('Бухгалтерия', (SELECT
                                                                   id
                                                                 FROM department
                                                                 WHERE name = 'Дочерняя компания'));
INSERT INTO department (name, parent_id) VALUES ('Юридический отдел', (SELECT
                                                                         id
                                                                       FROM department
                                                                       WHERE name = 'Дочерняя компания'));
INSERT INTO department (name, parent_id) VALUES ('АХО', (SELECT
                                                           id
                                                         FROM department
                                                         WHERE name =
                                                               'Дочерняя компания'));
INSERT INTO department (name, parent_id) VALUES ('Охрана', (SELECT
                                                              id
                                                            FROM department
                                                            WHERE name =
                                                                  'АХО'));

