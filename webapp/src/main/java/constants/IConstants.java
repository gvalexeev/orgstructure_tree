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
package constants;

/**
 * $Id
 * <p>Title: Интерфейс с константами</p>
 * <p>Description: </p>
 * <p>Author: g.alexeev (g.alexeev@i-teco.ru)</p>
 * <p>Date: 24.08.13</p>
 *
 * @version 1.0
 */
public interface IConstants {
    public interface Queries {
        public static final String RECURSIVE_TREE_PATH_QUERY = "RECURSIVE_TREE_PATH_QUERY";
        public static final String NODE_WITH_CHILDREN = "NODE_WITH_CHILDREN";
        public static final String ROOT_NODE_WITH_CHILDREN = "ROOT_NODE_WITH_CHILDREN";

        public static final String CREATE_DEPARTMENT = "CREATE_DEPARTMENT";
        public static final String LIST_DEPARTMENTS_WITH_LIKE_EXPR = "LIST_DEPARTMENTS_WITH_LIKE_EXPR";
        public static final String CREATE_EMPLOYEE = "CREATE_EMPLOYEE";
        public static final String DELETE_DEPARTMENT = "DELETE_DEPARTMENT";
        public static final String DELETE_EMPLOYEE = "DELETE_EMPLOYEE";
    }

    public interface Tables {
        public static final String DEPARTMENT = "department";
        public static final String EMPLOYEE = "employee";
    }

    public interface Views {
        public static final String DEPARTMENTS_AND_EMPLOYEES_VIEW = "departments_and_employees_view";
        public static final String RELATION_PATH_VIEW = "relation_path_view";
    }

    public interface Operation {
        public static final String DELETE = "delete";
        public static final String CREATE = "create";
    }

    public interface DBConfig {
        public static final String HOST = "host";
        public static final String DATABASE = "database";
        public static final String USER = "user";
        public static final String PASSWORD = "password";

    }
}
