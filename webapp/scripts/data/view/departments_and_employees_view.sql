CREATE OR replace VIEW departments_and_employees_view AS (
  SELECT dep.id as id, dep.name as name, dep.parent_id as parent_id
  FROM department dep
  UNION
  SELECT empl.id, empl.fio, empl.dep_id
  FROM employee empl
);