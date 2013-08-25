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

import constants.IConstants;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class Utils {
    public static int checkOperationType(String operation) {
        if (IConstants.Operation.CREATE.equalsIgnoreCase(operation)) {
            return 1;
        } else if (IConstants.Operation.DELETE.equalsIgnoreCase(operation)) {
            return 2;
        }

        return 0;
    }

}
