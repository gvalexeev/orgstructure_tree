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
package dao.core;

import bean.Department;

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
public interface DepartmentDAO {
    public boolean create();

    public Department getDepartment(String id) throws SQLException;

    public List<Department> getDepartments();
}
