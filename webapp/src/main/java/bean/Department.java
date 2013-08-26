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
import org.codehaus.jettison.json.JSONException;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Queries;

import javax.naming.ConfigurationException;
import java.sql.SQLException;

/**
 * $Id
 * <p>Title: Класс-бин департамента</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.08.13</p>
 *
 * @version 1.0
 */
public class Department {
    private Integer id;
    private String name;
    private Integer parentId;

    public Department(String name, Integer parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * Метод создания нового департамента
     * @param dep - департамент
     * @return - булево значение успешности проведения транзакции
     * @throws SQLException
     * @throws JSONException
     * @throws ConfigurationException
     */
    public static boolean create(Department dep) throws Exception {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        int result = template.update(
                Queries.getQuery(IConstants.Queries.CREATE_DEPARTMENT),
                dep.getName(),
                dep.getParentId()
        );

        return result != 0;
    }

    /**
     * Удаление департамента с выбранным id
     * @param id - id департамента
     * @return
     * @throws Exception
     */
    public static boolean delete(int id) throws Exception {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        int result = template.update(
                Queries.getQuery(IConstants.Queries.DELETE_DEPARTMENT),
                id
        );

        return result != 0;
    }
}
