CREATE TABLE department
(
  id        INTEGER                 NOT NULL DEFAULT nextval('id_seq' :: REGCLASS),
  name      CHARACTER VARYING(1024) NOT NULL,
  parent_id INTEGER,
  CONSTRAINT department_id PRIMARY KEY (id),
  CONSTRAINT parent_id FOREIGN KEY (parent_id)
  REFERENCES department (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS = FALSE
);
ALTER TABLE department
OWNER TO admin;
