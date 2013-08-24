INSERT INTO employee (first_name, last_name, middle_name, fio, dep_id)
  VALUES
  ('Герард', 'Алексеев', 'Викторович', 'Алексеев Г.В.',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Корпоративных Систем')),
  ('Игорь', 'Заурядников', 'Мартынович', 'Заурядников И.М.',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Финансового Благополучия')),
  ('Бирюлька', 'Петрова', 'Алексеевна', 'Петрова Б.А.',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Большущих Прибылей')),
  ('Абвгдейка', 'Николаева', 'Алексеевна', 'Николаева А.А.',
   (SELECT
      id
    FROM department
    WHERE name = 'Департамент Большущих Прибылей'));