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

import constants.IConstants;
import factory.JdbcTemplateFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import utils.Queries;
import validation.Checker;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 19.08.13</p>
 *
 * @version 1.0
 */
//TODO: refactoring
public class DataToJSONProcessor {
    private static final String SQL_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id = ? UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id = ?";
    private static final String SQL_EMPTY_DATA = "SELECT 1 AS c, dep.id, dep.name FROM department dep WHERE dep.parent_id IS null UNION SELECT 2 AS c, empl.id, empl.fio FROM employee empl WHERE empl.dep_id IS null";

    public static String getDepartmentsList(String likeValue) throws JSONException, SQLException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();
        //TODO: говно-код дальше пошел
        StringBuilder departmentSQLBuilder = new StringBuilder("SELECT id, name FROM department ").
                append("WHERE name like '%").
                append(Checker.isNull(likeValue) ? "" : likeValue).
                append("%'");

        SqlRowSet result = template.queryForRowSet(departmentSQLBuilder.toString());

        JSONArray jArray = new JSONArray();
        while (result.next()) {
            String name = result.getString("name");
            int node = result.getInt("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", node);
            jObj.put("label", name);
            jObj.put("type", "department");
            jArray.put(jObj);
        }
        return jArray.toString();
    }

    public static String getNodeWithChildren(String nodeId) throws SQLException, JSONException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        SqlRowSet result;
        if (Checker.isNull(nodeId)) {
            result = template.queryForRowSet(SQL_EMPTY_DATA);
        } else {
            int idIntValue = Integer.parseInt(nodeId);
            result = template.queryForRowSet(SQL_DATA, idIntValue, idIntValue);
        }

        JSONArray jArray = new JSONArray();
        while (result.next()) {
            String name = result.getString("name");
            int node = result.getInt("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", node);
            jObj.put("label", name);

            //При добавлении других типов узлов, возможно переписать на switch
            if ("1".equals(result.getString("c"))) {
                jObj.put("load_on_demand", true);
                jObj.put("type", "department");
            } else {
                jObj.put("type", "employee");
            }

            jArray.put(jObj);
        }
        return jArray.toString();
    }

    public static String getSearchValsAsJSON(String searchParam) throws SQLException, JSONException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        StringBuilder sqlBuilder = new StringBuilder(Queries.getQuery(IConstants.Queries.RECURSIVE_TREE_PATH_QUERY)).
                append(" WHERE name like '%").
                append(searchParam).
                append("%'");

        SqlRowSet set = template.queryForRowSet(sqlBuilder.toString());

        JSONArray array = new JSONArray();
        while(set.next()) {
            int level = set.getInt("level");
            List<String> idArray = Arrays.asList(set.getString("id_path").split("/"));

            int id = set.getInt("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", id);
            jObj.put("path", new JSONArray(idArray));
            jObj.put("level", level);
            array.put(jObj);
        }

        return array.toString();
    }
}
