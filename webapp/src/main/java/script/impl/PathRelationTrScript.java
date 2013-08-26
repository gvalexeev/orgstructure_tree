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
import java.util.Arrays;
import java.util.List;

/**
 * $Id
 * <p>Title: Класс-обертка скриптов для проведения операций с представлением path_relation_view</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class PathRelationTrScript implements ITransactionScript {
    private String searchVal;

    /**
     *
     * @param searchVal - значение для поиска в таблице относительных путей объектов.
     *                  Проверяется его содержание в поле name
     */
    public PathRelationTrScript(String searchVal) {
        this.searchVal = searchVal;
    }

    @Override
    public String run() throws SQLException, JSONException, ConfigurationException {
        String resultJSON = "{}";
        if (!Utils.isNull(searchVal)) {
            resultJSON = getSearchValsAsJSON();
        }
        return resultJSON;
    }

    /**
     * Получение данных поиска в виде json-структуры.
     * структура json:
     * {
     *     id,  -айди найденного элемента
     *     level, -уровень на котором находится элемент
     *     [path_id] -массив айдишек идентичных пути, по которому расположен элемент
     * }
     * @return json
     * @throws SQLException
     * @throws JSONException
     * @throws ConfigurationException
     */
    private String getSearchValsAsJSON() throws SQLException, JSONException, ConfigurationException {
        JdbcTemplate template = JdbcTemplateFactory.getDBTemplate();

        String sql = Queries.getQuery(IConstants.Queries.RECURSIVE_TREE_PATH_QUERY).replace(":like_expr", searchVal);
        SqlRowSet set = template.queryForRowSet(sql);

        JSONArray array = new JSONArray();
        while (set.next()) {
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
