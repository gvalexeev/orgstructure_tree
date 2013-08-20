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

import java.util.List;

/**
 * $Id
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 17.08.13</p>
 *
 * @version 1.0
 */
public class Department {
    @JsonProperty(value = "id")
    private Integer id;
    @JsonProperty(value = "label")
    private String name;
    private List<Employee> employees;
    private List<Department> departments;

    @JsonProperty(value = "load_on_demand")
    private final boolean loadOnDemand = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void addEmployee(Employee empl) {
        employees.add(empl);
    }

    public void addDepartment(Department dep) {
        departments.add(dep);
    }

    public boolean isLoadOnDemand() {
        return loadOnDemand;
    }
}
