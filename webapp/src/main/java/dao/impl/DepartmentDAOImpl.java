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

import bean.Department;
import dao.core.DepartmentDAO;

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
public class DepartmentDAOImpl implements DepartmentDAO {
    private static final String SQL_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id = ? UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id = ?";
    private static final String SQL_EMPTY_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id IS null UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id IS null";

    @Override
    public boolean create() {
        return true;
    }

    @Override
    public Department getDepartment(String id) throws SQLException {
        return null;
    }

    @Override
    public List<Department> getDepartments() {
        return null;
    }
}
