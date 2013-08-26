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
 * <p>Title: Утилитарный класс, содержащий полезные методы</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 25.08.13</p>
 *
 * @version 1.0
 */
public class Utils {
    /**
     * Проверка типа операции
     *
     * @param operation - строковое значение вида операции
     * @return 1 - операция создания, 2 - операция удаления
     */
    public static int checkOperationType(String operation) {
        if (IConstants.Operation.CREATE.equalsIgnoreCase(operation)) {
            return 1;
        } else if (IConstants.Operation.DELETE.equalsIgnoreCase(operation)) {
            return 2;
        }

        return 0;
    }

    /**
     * Метод проверки строки на пустоту
     *
     * @param value - строковое значение
     * @return - возвращает строку с ошибкой или null, если все в порядке
     */
    public static boolean isNull(String value) {
        return (value == null) || (value.trim().length() == 0);
    }
}
