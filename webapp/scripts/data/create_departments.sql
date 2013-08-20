INSERT INTO department (name, parent_id) VALUES ('Iteco', null);
INSERT INTO department (name, parent_id) VALUES ('Департамент Корпоративных Систем', (SELECT
                                                                                        id
                                                                                      FROM department
                                                                                      WHERE parent_id IS null));
INSERT INTO department (name, parent_id) VALUES ('Департамент Финансового Благополучия', (SELECT
                                                                                        id
                                                                                      FROM department
                                                                                      WHERE parent_id IS null));
INSERT INTO department (name, parent_id) VALUES ('Департамент Большущих Прибылей', (SELECT
                                                                                        id
                                                                                      FROM department
                                                                                      WHERE name = 'Департамент Финансового Благополучия'));

