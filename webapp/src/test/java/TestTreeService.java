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
import service.TreeService;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.08.13</p>
 *
 * @version 1.0
 */
public class TestTreeService {
    @Test
    public void testGetTreeJson() throws Exception {
        TreeService service = new TreeService();
    }

    @Test
    public void testGetBasicTree() throws Exception {
        TreeService service = new TreeService();
        System.out.println(service.getBasicTree(null));
    }
}
