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
import validation.Checker;

import javax.naming.ConfigurationException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class PathRelationTrScript implements ITransactionScript {
    private String searchVal;

    public PathRelationTrScript(String searchVal) {
        this.searchVal = searchVal;
    }

    @Override
    public String run() throws SQLException, JSONException, ConfigurationException {
        String resultJSON = "{}";
        if (!Checker.isNull(searchVal)) {
            resultJSON = getSearchValsAsJSON();
        }
        return resultJSON;
    }

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
