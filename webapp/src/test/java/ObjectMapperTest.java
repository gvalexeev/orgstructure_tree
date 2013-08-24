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

import bean.Department;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 18.08.13</p>
 *
 * @version 1.0
 */
public class ObjectMapperTest {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Department dep = new Department();
        dep.setName("I-Teco");
//        dep.addEmployee(new Employee("Gerard"));
//        dep.addEmployee(new Employee("Nikita"));
//        dep.addEmployee(new Employee("Alex"));

        ArrayList<Department> arr = new ArrayList<Department>();
        arr.add(dep);
        String val = mapper.writeValueAsString(arr);
        System.out.println(val);
    }
}
