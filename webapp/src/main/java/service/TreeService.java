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

import bean.Department;
import bean.Employee;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import script.core.ITransactionScript;
import script.impl.DepartmentTrScript;
import script.impl.PathRelationTrScript;
import utils.Utils;
import validation.Checker;

import javax.naming.ConfigurationException;
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
    @Path("/node")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNodeById(@QueryParam(value = "node") int nodeId) {
        String jsonResultValue = "[{\"label\":\"No data\"}]";

        try {
            ITransactionScript trScript = new DepartmentTrScript(nodeId);
            jsonResultValue = trScript.run();
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        } catch (ConfigurationException e) {
            log.error(e);
        }

        return jsonResultValue;
    }

    @GET
    @Path("/term")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDepartmentsWithTerm(@QueryParam(value = "term") String term) {
        String jsonResultValue = "[{\"label\":\"No data\"}]";

        try {
            if (!Checker.isNull(term)) {
                ITransactionScript trScript = new DepartmentTrScript(term);
                jsonResultValue = trScript.run();
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        } catch (ConfigurationException e) {
            log.error(e);
        }
        return jsonResultValue;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String performOperation(@FormParam("json_data") JSONObject data) {
        boolean result = false;

        try {
            if (data.has("type") && data.has("operation")) {
                switch (Utils.checkOperationType(data.getString("operation"))) {
                    case 1:
                        result = performCreateOperation(data);
                        break;
                    case 2:
                        result = performDeleteOperation(data);
                        break;
                }
            }
        } catch (JSONException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } catch (ConfigurationException e) {
            log.error(e);
        }

        return result ? "success" : "fail";
    }

    private boolean performCreateOperation(JSONObject data) throws JSONException, SQLException, ConfigurationException {
        String type = data.getString("type");

        if ("employee".equalsIgnoreCase(type)) {
            Employee employee = new Employee(
                    data.getString("first_name"),
                    data.getString("last_name"),
                    data.getString("middle_name"),
                    data.getInt("department_id")
            );

            return Employee.create(employee);
        } else if ("department".equalsIgnoreCase(type)) {
            Department department = new Department(
                    data.getString("name"),
                    data.getInt("parent_id")
            );

            return Department.create(department);
        }

        return false;
    }

    private boolean performDeleteOperation(JSONObject data) throws JSONException, SQLException, ConfigurationException {
        String type = data.getString("type");
        int id = data.getInt("id");

        if ("employee".equalsIgnoreCase(type)) {
            return Employee.delete(id);
        } else if ("department".equalsIgnoreCase(type)) {
            return Department.delete(id);
        }

        return false;
    }

    @GET
    @Path("/search")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSearchResults(@QueryParam(value = "s") String searchTerm) {
        String jsonResultValue = "[{\"label\":\"No data\"}]";
        try {
            if (!Checker.isNull(searchTerm)) {
                ITransactionScript trScript = new PathRelationTrScript(searchTerm);
                jsonResultValue = trScript.run();
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        } catch (ConfigurationException e) {
            log.error(e);
        }
        return jsonResultValue;
    }
}
