<%--
  Created by IntelliJ IDEA.
  User: aming
  Date: 2021-10-29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<HTML>
<HEAD>
    <TITLE> ZTREE DEMO - checkbox</TITLE>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/ztree/js/jquery.ztree.excheck-3.5.js"></script>

    <SCRIPT type="text/javascript">
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes = ${authorJson};

        var ztree;
        $(function(){
            ztree = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            //让所有的选项展开
            ztree.expandAll(true);

            $("#form1").submit(function () {
                //获取当前选中的选项信息
                var checkedNodes = ztree.getCheckedNodes(true);

                $.each(checkedNodes,function () {
                    //this代表遍历到的对象 {id:1,pId:11,name:"",checked:""}
                    var module_id = this.id;
                    $("#form1").append('<input type="hidden" name="ids" value="'+module_id+'">');
                });
                return true;
            });
        });
    </SCRIPT>
</HEAD>

<BODY>
<ul id="treeDemo" class="ztree"></ul>

<form action="${pageContext.request.contextPath}/system/role?operation=author" id="form1" method="post">
    <input type="hidden" name="roleId" value="${roleId}">
    <input type="submit" value="授权">
</form>
</BODY>
</HTML>
