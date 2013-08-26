CREATE TABLE employee
(
  id integer NOT NULL DEFAULT nextval('id_seq'::regclass),
  first_name character varying(1024) NOT NULL,
  middle_name character varying(1024) NOT NULL,
  last_name character varying(1024) NOT NULL,
  fio character varying(1024),
  dep_id integer NOT NULL,
  CONSTRAINT employee_id PRIMARY KEY (id ),
  CONSTRAINT dep_id FOREIGN KEY (dep_id)
      REFERENCES department (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE employee
  OWNER TO admin;

CREATE INDEX fki_dep_id
  ON employee
  USING btree
  (dep_id );