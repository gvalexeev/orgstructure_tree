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
package script.impl;

import constants.IConstants;
import factory.JdbcTemplateFactory;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import script.core.ITransactionScript;
import utils.Queries;
import utils.Utils;

import javax.naming.ConfigurationException;
import java.sql.SQLException;

/**
 * $Id
 * <p>Title: Класс-обертка скриптов для проведения операций с департаментами</p>
 * <p>Description: Конфигурируется через конструкторы. После должен быть вызван метод run для проведения операции</p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class DepartmentTrScript implements ITransactionScript {
    private int nodeId;
    private String likeValue;

    /**
     * Инициализация скрипта с айди ноды. Может быть ноль.
     * @param nodeId - айди ноды, структуру которой необходимо вернуть
     */
    public DepartmentTrScript(int nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * Инициализация скрипта с выражением like для запроса.
     * @param likeValue - выражение, которому должны удовлетворять объекты в базе
     */
    public DepartmentTrScript(String likeValue) {
        this.likeValue = likeValue;
    }

    @Override
    public String run() throws JSONException, SQLException, ConfigurationException {
        String result;
        if (!Utils.isNull(likeValue)) {
            result = getDepsAsJSON();
        } else {
            result = getNodeWithChildren();
        }

        return result;
    }

    /**
     * Получение департаментов удовлетворяющих условию like в виде json-структуры
     * @return
     * @throws JSONException
     * @throws SQLException
     * @throws ConfigurationException
     */
    private String getDepsAsJSON() throws JSONException, SQLException, ConfigurationException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        String sql = Queries.getQuery(IConstants.Queries.LIST_DEPARTMENTS_WITH_LIKE_EXPR).replace(":like_expr", likeValue);
        SqlRowSet result = template.queryForRowSet(sql);

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

    /**
     * Получение ноды дерева вместе с детьми. Если айди ноды равен нулю, то возвращаются корневые ноды.
     * @return - json структура
     * @throws SQLException
     * @throws JSONException
     * @throws ConfigurationException
     */
    private String getNodeWithChildren() throws SQLException, JSONException, ConfigurationException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        SqlRowSet result;
        if (nodeId != 0) {
            result = template.queryForRowSet(
                    Queries.getQuery(IConstants.Queries.NODE_WITH_CHILDREN),
                    nodeId
            );
        } else {
            result = template.queryForRowSet(
                    Queries.getQuery(IConstants.Queries.ROOT_NODE_WITH_CHILDREN)
            );
        }


        JSONArray jArray = new JSONArray();
        while (result.next()) {
            String name = result.getString("name");
            String type = result.getString("type");
            int node = result.getInt("id");

            JSONObject jObj = new JSONObject();
            jObj.put("id", node);
            jObj.put("label", name);
            jObj.put("type", type);

            if ("department".equals(type)) {
                jObj.put("load_on_demand", true);
            }

            jArray.put(jObj);
        }
        return jArray.toString();
    }


}
