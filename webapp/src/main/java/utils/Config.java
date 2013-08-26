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

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * $Id
 * <p>Title: утилитарный класс, содержащий методы для получения значений из файла конфига.</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class Config {
    private static final String propFileName = "config.properties";
    private static Properties props;

    public static Properties getConfig() throws ConfigurationException {
        InputStream is =
                Queries.class.getResourceAsStream("/" + propFileName);
        if (is == null) {
            throw new ConfigurationException("Unable to load property file: " + propFileName);
        }
        //singleton
        if (props == null) {
            props = new Properties();
            try {
                props.load(is);
            } catch (IOException e) {
                throw new ConfigurationException("Unable to load property file: " + propFileName + "\n" + e.getMessage());
            }
        }
        return props;
    }

    /**
     * Получение значения из файла конфига по имени
     * @param config
     * @return
     * @throws ConfigurationException
     */
    public static String getConfig(String config) throws ConfigurationException {
        return getConfig().getProperty(config);
    }
}
