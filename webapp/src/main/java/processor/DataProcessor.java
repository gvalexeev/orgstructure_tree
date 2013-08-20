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
package processor;

import factory.JdbcTemplateFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import validation.Checker;

import java.sql.SQLException;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 19.08.13</p>
 *
 * @version 1.0
 */
public class DataProcessor {
    private static final String SQL_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id = ? UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id = ?";
    private static final String SQL_EMPTY_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id IS null UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id IS null";

    public static String getTreeDataFromId(String id) throws SQLException, JSONException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        SqlRowSet result;
        if (Checker.isNull(id)) {
            result = template.queryForRowSet(SQL_EMPTY_DATA);
        } else {
            int idIntValue = Integer.parseInt(id);
            //TODO: параметры для preparedStatement. Может быть есть именные параметры. Проверить!
            result = template.queryForRowSet(SQL_DATA, idIntValue, idIntValue);
        }

        JSONArray jArray = new JSONArray();
        while (result.next()) {
            String name = result.getString("name");
            String node = result.getString("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", node);
            jObj.put("label", name);
            if ("1".equals(result.getString("c"))) {
                jObj.put("load_on_demand", true);
            }
            jArray.put(jObj);
        }

        return jArray.toString();
    }

    public static String getDepartmentById(String id) {
        return null;
    }

    public static String getDepartments(String likeValue) throws JSONException, SQLException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();
        //TODO: говно-код дальше пошел
        StringBuilder departmentSQLBuilder = new StringBuilder("SELECT id, name FROM department ");
        if (!Checker.isNull(likeValue)) {
           departmentSQLBuilder.append("WHERE name like '%").append(likeValue).append("%'");
        }


        SqlRowSet result = template.queryForRowSet(departmentSQLBuilder.toString());

        JSONArray jArray = new JSONArray();
        while (result.next()) {
            String name = result.getString("name");
            String node = result.getString("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", node);
            jObj.put("label", name);
            jArray.put(jObj);
        }
        return jArray.toString();
    }
}
