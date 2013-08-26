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
package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 * $Id
 * <p>Title: Утилитарный класс для работы с файлом-контейнером запросов</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 21.08.13</p>
 *
 * @version 1.0
 */
public class Queries {
    private static final String propFileName = "queries.properties";
    private static Properties props;

    public static Properties getQueries() throws SQLException {
        InputStream is =
                Queries.class.getResourceAsStream("/" + propFileName);
        if (is == null) {
            throw new SQLException("Unable to load property file: " + propFileName);
        }
        //singleton
        if (props == null) {
            props = new Properties();
            try {
                props.load(is);
            } catch (IOException e) {
                throw new SQLException("Unable to load property file: " + propFileName + "\n" + e.getMessage());
            }
        }
        return props;
    }

    public static String getQuery(String query) throws SQLException {
        return getQueries().getProperty(query);
    }

}
