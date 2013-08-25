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
package factory;

import constants.IConstants;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.Config;

import javax.naming.ConfigurationException;
import java.sql.SQLException;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 13.05.13</p>
 *
 * @version 1.0
 */
public class JdbcTemplateFactory {
    //TODO: to properties
    public static JdbcTemplate getDBTemplate() throws SQLException, ConfigurationException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerName(Config.getConfig(IConstants.DBConfig.HOST));
        dataSource.setDatabaseName(Config.getConfig(IConstants.DBConfig.DATABASE));
        dataSource.setUser(Config.getConfig(IConstants.DBConfig.USER));
        dataSource.setPassword(Config.getConfig(IConstants.DBConfig.PASSWORD));

        return new JdbcTemplate(dataSource);
    }
}
