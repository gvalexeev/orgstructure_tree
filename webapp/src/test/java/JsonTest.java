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

import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 22.08.13</p>
 *
 * @version 1.0
 */
public class JsonTest {
    @Test
    public void testName() throws Exception {
        JSONObject obj = new JSONObject();
        obj.put("arr", new String[]{"123","254"});
        System.out.println();
    }
}
