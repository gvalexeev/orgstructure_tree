CREATE OR replace VIEW relation_path_view AS (
  WITH RECURSIVE path(name, text_path, id_path, id, parent_id, level) AS (
    SELECT 
      name, '' || name, '' || id, id, parent_id, CAST ( 1 AS INTEGER )
    FROM 
      departments_and_employees_view 
    WHERE 
      parent_id is null
    UNION 
        SELECT
          dep_empl.name, 
            path.text_path || 
              CASE path.text_path 
                WHEN '/' THEN '' 
                ELSE '/' 
              END || dep_empl.name,
            path.id_path || 
              CASE path.text_path 
                WHEN '/' THEN '' 
                ELSE '/' 
                END || 
                dep_empl.id,
            dep_empl.id,
            dep_empl.parent_id,
            path.level + 1
        FROM departments_and_employees_view dep_empl, path
        WHERE dep_empl.parent_id = path.id)
  select * from path
);