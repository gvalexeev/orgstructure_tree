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
import processor.DataToJSONProcessor;
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
    public String getBasicTree(@QueryParam(value = "node") String nodeId,
                               @QueryParam(value = "term") String term) {
        String jsonResultValue = "[{\"label\":\"No data\"}]";

        try {
            jsonResultValue = !Checker.isNull(term) ?
                    DataToJSONProcessor.getDepartmentsList(term)
                    :
                    DataToJSONProcessor.getNodeWithChildren(nodeId);
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }

        return jsonResultValue;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String createObject(@FormParam("json_data") JSONObject data) {
        boolean result = true;

        System.out.println(data);
//        boolean result = false;
//
//        try {
//            if (data.has("type")) {
//                String type = data.getString("type");
//
//                if ("employee".equalsIgnoreCase(type)) {
//                    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//                    result = employeeDAO.create(new Employee("", "", "", 100));
//                } else if ("department".equalsIgnoreCase(type)) {
//                    DepartmentDAO depDAO = new DepartmentDAOImpl();
//                    result = depDAO.create();
//                }
//            }
//        } catch (JSONException e) {
//            log.error(e);
//        } catch (SQLException e) {
//            log.error(e);
//        }
//
        return result ? "success" : "fail";
    }

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSearchResults(@QueryParam(value = "s") String searchTerm) {
        String jsonResultValue = "[{\"label\":\"No data\"}]";
        try {
            if (!Checker.isNull(searchTerm)) {
                jsonResultValue = DataToJSONProcessor.getSearchValsAsJSON(searchTerm);
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }

        System.out.println(jsonResultValue);
        return jsonResultValue;
    }
}
