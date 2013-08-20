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
import processor.DataProcessor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 20.08.13</p>
 *
 * @version 1.0
 */
@Path("/data")
public class DataService {
    private Logger log = Logger.getRootLogger();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDepsAsJSON(@QueryParam(value = "term") String term) {
        try {
            return DataProcessor.getDepartments(term);
        } catch (SQLException e) {
            log.error(e);
        } catch (JSONException e) {
            log.error(e);
        }
        return "{}";
    }
}
