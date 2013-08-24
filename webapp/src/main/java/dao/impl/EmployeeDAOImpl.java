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
package dao.impl;

import bean.Employee;
import dao.core.EmployeeDAO;
import factory.JdbcTemplateFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 21.08.13</p>
 *
 * @version 1.0
 */
public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public boolean create(Employee employee) throws SQLException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();
        if (employee != null) {
        }

        return true;
    }

    @Override
    public Employee getEmployee(String id) {
        return null;
    }

    @Override
    public List<Employee> getEmployees() {
        return null;
    }
}
