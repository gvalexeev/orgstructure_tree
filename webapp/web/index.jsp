<%--***********************************************************************

 Эксклюзивные права i Teco, ЗАО.                                       
 (c) Copyright i-Teco, CJSK. 2013                                      
 All Rights reserved.                                                  
 Данные исходные коды не могут использоваться и быть изменены          
 без официального разрешения компании i-Teco                           

***************************************************************************

 Project                                                               
 Module                                                                
 File           ${NAME}                                                
 Description                                                           
 Created by     g.alexeev (g.alexeev@i-teco.ru)                            
 Created on     17.08.13                                                

***********************************************************************--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="utf-8"/>
<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.js"></script>
<script src="${pageContext.request.contextPath}/js/tree.jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/jqtree.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/ui-lightness/jquery-ui.css">
<title>Orgstructure_tree</title>
<style>
    body {
        font-size: 100%;
    }

    label, input {
        display: block;
    }

    input.text {
        margin-bottom: 12px;
        width: 95%;
        padding: .4em;
    }

    fieldset {
        padding: 0;
        border: 0;
        margin-top: 25px;
    }

    div#tree {
        border: 5px solid navy;
    }

    h1 {
        font-size: 1.2em;
        margin: .6em 0;
    }

    .ui-dialog .ui-state-error {
        padding: .3em;
    }

    .validateTips {
        border: 1px solid transparent;
        padding: 0.3em;
    }

    .li-searched {
        background: darkred;
    }
</style>
<script>
$(function () {
    var type = $(".hidden_type_val"),
            tips = $(".validateTips"),
            $tree = $("#tree"),
            allFields;

    function updateTips(t) {
        tips
                .text(t)
                .addClass("ui-state-highlight");
        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength(o, n, min) {
        if (o.val().length < min) {
            o.addClass("ui-state-error");
            //TODO
            updateTips("Length of " + n + " must be not less " +
                    min + ".");
            return false;
        } else {
            return true;
        }
    }

    function checkId(o) {
        if (o.val == null) {
            o.addClass("ui-state-error");
            updateTips("Данное поле не заполнено.");
            return false;
        } else {
            return true;
        }
    }

    $("#dialog-form").dialog({
        closeOnEscape: true,
        autoOpen: false,
        height: 500,
        width: 350,
        modal: true,
        buttons: [
            {
                id: 'Create',
                text: "Создать",
//                disabled: true,
                click: function () {
                    var $this = $(this),
                            bValid = true,
                            json_string,
                            autocomplete_id = $(".hidden_autocomplete_val");

                    if ("employee" == type.val()) {
                        var first_name = $("#first_name"),
                                last_name = $("#last_name"),
                                middle_name = $("#middle_name"),
                                allEmplFields = $([]).add(first_name).add(last_name).add(middle_name);

                        allEmplFields.removeClass("ui-state-error");
                        allFields = allEmplFields;

                        bValid = bValid && checkLength(first_name, "first_name", 3);
                        bValid = bValid && checkLength(last_name, "last_name", 3);
                        bValid = bValid && checkLength(middle_name, "middle_name", 3);
                        bValid = bValid && checkId(autocomplete_id);

                        json_string = JSON.stringify(
                                {
                                    operation: "create",
                                    type: type.val(),
                                    first_name: first_name.val(),
                                    last_name: last_name.val(),
                                    middle_name: middle_name.val(),
                                    department_id: autocomplete_id.val()
                                });
                    } else if ("department" == type.val()) {
                        var dep_name = $('#dep_name'),
                                all_dep_fields = $([]).add(dep_name);
                        all_dep_fields.removeClass("ui-state-error");

                        bValid = bValid && checkLength(dep_name, "Department name", 3);
                        bValid = bValid && checkId(autocomplete_id);


                        json_string = JSON.stringify(
                                {
                                    operation: "create",
                                    type: type.val(),
                                    name: dep_name.val(),
                                    parent_id: autocomplete_id.val()
                                });
                    }

                    if (bValid) {
                        $.post(
                                '${pageContext.request.contextPath}/rest/tree/',
                                {
                                    json_data: json_string
                                },
                                function (data) {
                                    if (data == 'success') {
                                        $this.dialog("close");
                                    }
                                }
                        );
                    }
                }
            },
            {
                text: 'Cancel',
                click: function () {
                    $(this).dialog("close");
                }
            }
        ],
        close: function () {
//            allFields.val("").removeClass("ui-state-error");
            location.href = location.href;
        }

    });

//Button events
    $("#create_button")
            .button()
            .click(function () {
                $("#dialog-form").dialog("open");
            });

    $("#delete_button")
            .button({icon: {primary: ".ui-icon-trash"}})
            .hide()
            .click(function () {
                var node = $tree.tree('getSelectedNode');
                if (node != false) {
//                    var parent_node = node.parent;

                    if (node.isFolder()) {
                        if (!node.is_open && node.load_on_demand) {
                            $tree.tree("openNode", node)
                                    .done(function () {
                                        postDeleteOperation(node);
                                    }
                            );
                        } else {
                            postDeleteOperation(node);
                        }
                    } else {
                        postDeleteOperation(node);
                    }
                }
            }
    );

    function postDeleteOperation(node) {
        if (!node.hasChildren()) {
            $.post(
                    '${pageContext.request.contextPath}/rest/tree/',
                    {
                        json_data: JSON.stringify(
                                {
                                    operation: "delete",
                                    type: node.type,
                                    id: node.id
                                })
                    },
                    function (data) {
                        //TODO нормальное обновление страницы или обновление родительской ноды
                        location.href = location.href;
                    }
            );
        } else {
            $("#message-form").dialog("open");
        }
    }

    $("#message-form").dialog({
        closeOnEscape: true,
        height: 400,
        width: 350,
        autoOpen: false,
        modal: true,
        buttons: {
            Ok: function () {
                $(this).dialog("close");
            }
        }
    });

//Поиск TODO
    $("#search_button")
            .button()
            .click(function () {
                var bVal = true;
                var searchVal = $("#search").find("input");

                bVal = bVal && checkLength(searchVal, "search_field", 3);

                if (bVal) {
                    $.getJSON(
                            '${pageContext.request.contextPath}/rest/tree/search/?s=' + searchVal.val(),
                            function (data) {
                                $.each(data, function (index, item) {
                                    $.each(item.path, function (index, path_id) {
                                        var node = $tree.tree('getNodeById', path_id);

                                        if (node.isFolder()) {
                                            if (!node.is_open && node.load_on_demand) {
                                                $tree.tree("openNode", node)
                                                        .done(function () {
                                                            $(node.element).addClass(".li-searched");
                                                        });
                                            } else {
                                                $(node.element).addClass(".li-searched");
                                            }
                                        } else {
                                            $(node.element).addClass(".li-searched");
                                        }
                                    });
                                });
                            });
                }
            }
    );



//Selector change
    $('#type_selector').change(function () {
        var x = $(this).val();
        $('#type_selector').hide();

        if ("department" == x) {
            $('#create_department').show();
        } else if ("employee" == x) {
            $('#create_user').show();
        }

        $('.hidden_type_val').val(x);

//        var buttons = $("#dialog-form").dialog("option", "buttons");
//        $.each(buttons, function (index, button) {
//             if () {
//
//             }
//        });

    });


    $('#tree').bind(
            'tree.select',
            function (event) {
                var $deleteButton = $("#delete_button");
                event.node ? $deleteButton.show() : $deleteButton.hide();
            }
    );

//Autocomplete realization
    $(".custom_autocomplete").autocomplete({
        source: function (request, response) {
            $.getJSON(
                    '${pageContext.request.contextPath}/rest/tree/term/?term=' + request.term,
                    function (data) {
                        response($.map(data, function (item) {
                            return {
                                label: item.label,
                                value: item.label,
                                dep_id: item.id
                            }
                        }));
                    }
            );
        },
        minLength: 2,
        select: function (event, ui) {
            $('.hidden_autocomplete_val').val(ui.item.dep_id);
        }
    });
})
;
</script>
</head>
<body>


<div id="search" style="width:10%; margin:0; float : left">
    <input type="text" name="search_field" id="search_field" class="text ui-widget-content ui-corner-all"/>
</div>
<div id="buttons">
    <%--TODO: иконки кнопкам--%>
    <button id="search_button">Поиск</button>
    <button id="create_button">Создать</button>
    <button id="delete_button">Удалить</button>
</div>
<div id="dialog-form" title="Create node">
    <p class="validateTips">All form fields are required.</p>

    <form id="selector">
        <label for="type_selector">Select object to create</label>
        <select id="type_selector" name="type_selector">
            <option value="">Choose value</option>
            <option value="department">Department</option>
            <option value="employee">Employee</option>
        </select>
    </form>

    <form id="create_user" hidden>
        <fieldset>
            <label for="first_name">First name</label>
            <input type="text" name="first_name" id="first_name" class="text ui-widget-content ui-corner-all"/>
            <label for="middle_name">Middle name</label>
            <input type="text" name="middle_name" id="middle_name" class="text ui-widget-content ui-corner-all"/>
            <label for="last_name">Last name</label>
            <input type="text" name="last_name" id="last_name" class="text ui-widget-content ui-corner-all"/>
            <label for="department">Department</label>
            <input type="text" name="department" id="department"
                   class="text ui-widget-content ui-corner-all custom_autocomplete"/>
            <input type="hidden" value="null" id="dep_id" class="hidden_autocomplete_val"/>
            <input type="hidden" value="null" id="emp_type" class="hidden_type_val"/>
        </fieldset>
    </form>

    <form id="create_department" hidden>
        <fieldset>
            <label for="dep_name">Department name</label>
            <input type="text" name="dep_name" id="dep_name"
                   class="text ui-widget-content ui-corner-all"/>
            <label for="parent_dep_name">Parent department name</label>
            <input type="text" name="parent_dep_name" id="parent_dep_name"
                   class="text ui-widget-content ui-corner-all custom_autocomplete"/>
            <input type="hidden" id="parent_dep_id" class="hidden_autocomplete_val"/>
            <input type="hidden" value="null" id="dep_type" class="hidden_type_val"/>
        </fieldset>
    </form>
</div>

<div id="message-form" title="Message">
    <p>
        <span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>
        Невозможно удалить узел! Удостоверьтесь что узел не содержит дочерние элементы
    </p>
</div>

<div id="tree" style="width:100%; margin:0; padding: 10px">
    <script type="text/javascript">
        $.getJSON(
                '${pageContext.request.contextPath}/rest/tree/node/',
                function (data) {
                    $('#tree').tree({
                        dataUrl: '${pageContext.request.contextPath}/rest/tree/node/',
//                        saveState: true,
                        data: data,
                        autoOpen: false
                    });
                }
        );
    </script>
</div>
</body>
</html>