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
package bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.08.13</p>
 *
 * @version 1.0
 */
public class Employee {
    private Integer id;
    @JsonProperty(value = "label")
    private String first_name;
    private String last_name;
    private String middle_name;
    private Integer dep_id;

    public Employee(String first_name, String last_name, String middle_name, Integer dep_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.dep_id = dep_id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public Integer getDep_id() {
        return dep_id;
    }
}
