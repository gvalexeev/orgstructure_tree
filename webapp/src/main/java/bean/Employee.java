/*
* $Id
*
* (C) Copyright 1997 i-Teco, CJSK. All Rights reserved.
* i-Teco PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*
* Эксклюзивные права 1997 i-Teco, ЗАО.
* Данные исходные коды не могут использоваться и быть изменены
* без официального разрешения компании i-Teco.          
*/
package bean;

import constants.IConstants;
import factory.JdbcTemplateFactory;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jettison.json.JSONException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Queries;

import javax.naming.ConfigurationException;
import java.sql.SQLException;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.08.13</p>
 *
 * @version 1.0
 */
public class Employee {
    private Integer id;
    @JsonProperty(value = "label")
    private String firstName;
    private String lastName;
    private String middleName;
    private String fio;
    private Integer depId;

    public Employee(String first_name, String last_name, String middle_name, Integer dep_id) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.middleName = middle_name;
        this.depId = dep_id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public static boolean create(Employee employee) throws SQLException, JSONException, ConfigurationException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        int result = template.update(
                Queries.getQuery(IConstants.Queries.CREATE_EMPLOYEE),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getMiddleName(),
                employee.getDepId()
        );

        return result != 0;
    }

    public static boolean delete(int id) throws SQLException, JSONException, ConfigurationException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        int result = template.update(
                Queries.getQuery(IConstants.Queries.DELETE_EMPLOYEE),
                id
        );

        return result != 0;
    }
}
