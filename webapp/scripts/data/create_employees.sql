INSERT INTO employee (first_name, last_name, middle_name, dep_id)
  VALUES
  ('Герард', 'Алексеев', 'Викторович',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Корпоративных Систем')),
  ('Игорь', 'Заурядников', 'Мартынович',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Финансового Благополучия')),
  ('Бирюлька', 'Петрова', 'Алексеевна',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Большущих Прибылей')),
  ('Абвгдейка', 'Николаева', 'Алексеевна',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Большущих Прибылей'));