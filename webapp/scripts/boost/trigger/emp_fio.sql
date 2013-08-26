CREATE OR REPLACE FUNCTION emp_fio() RETURNS trigger AS $emp_fio$
    BEGIN

        NEW.fio := NEW.last_name || ' ' || LEFT(NEW.first_name, 1) || '. ' || LEFT(NEW.middle_name, 1) || '.';
        RETURN NEW;
    END;
$emp_fio$ LANGUAGE plpgsql;


CREATE TRIGGER emp_fio BEFORE INSERT OR UPDATE ON employee
    FOR EACH ROW EXECUTE PROCEDURE emp_fio();