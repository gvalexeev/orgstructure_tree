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

import org.junit.Test;
import processor.DataProcessor;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 20.08.13</p>
 *
 * @version 1.0
 */
public class DataProcessorTest {
    @Test
    public void testGetDepartments() throws Exception {
        String json = DataProcessor.getDepartments("Кор");
        System.out.println(json);

    }
}
