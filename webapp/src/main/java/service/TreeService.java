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
package service;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import processor.DataProcessor;
import validation.Checker;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
@Path("/tree")
public class TreeService {
    private Logger log = Logger.getRootLogger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBasicTree(@QueryParam(value = "node") String nodeId) {
        try {
            String jsonResultValue = DataProcessor.getTreeDataFromId(nodeId);
            if (!Checker.isNull(jsonResultValue)) {
                return jsonResultValue;
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }

        return "[{\"label\":\"No data\"}]";
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String doPost(@FormParam("json_data") JSONObject object) {
        try {
            if (object != null) {
                System.out.println("Type: " + (object.has("type") ? object.get("type") : null));
                System.out.println("Name: " + (object.has("name") ? object.get("name") : null));
            }
        } catch (JSONException e) {
            log.error(e);
            return "false";
        }
        return "success";
    }
}
