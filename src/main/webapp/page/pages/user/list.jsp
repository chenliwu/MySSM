<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/14
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/page/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/page/jqGrid-4.15.2/dist/css/ui.jqgrid.min.css"/>

</head>
<body>

<div class="col-md-8">
    <div class="text-center">
        <button type="button" id="search" class="btn btn-primary">查询</button>
        <button type="reset" class="btn btn-default">重置</button>
    </div>
</div>

<div class="box-body">
    <table id="jqGrid"></table>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/page/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/page/jqGrid-4.15.2/dist/jquery.jqgrid.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/page/jqGrid-4.15.2/dist/jquery.jqgrid.src.js"></script>
<script>

    $(function () {
        var url = '<%=request.getContextPath()%>/api/user/queryList';
        console.log("url:"+url);
        var jqGrid = $("#jqGrid").jqGrid({
            url: url,
            styleUI: 'Bootstrap',
            dataType:'json',
            multiselect: false,
            frozen: true,
            colModel: [
                {
                    label: '操作',
                    sortable: false,
                    frozen: true,
                    formatter: function (cellvalue, options, rowObject) {
                        var html = '<a href="javascript:void(0);" data-row="' + rowObject.id + '" class="update">修改</a>';
                        html += '<span class="text-explode">|</span>';
                        html += '<a href="javascript:void(0);" data-row="' + rowObject.id + '" class="delete">删除</a>';
                        return html;
                    }
                },
                {label: '', name: 'id'},
                {label: '用户名', name: 'username'},
                {label: '密码', name: 'password'}
            ],
        });


        // 删除操作
        $(document).on('click', 'button[id="delete"],a.delete', function () {
            var rowId = $(this).data("row");
            if (rowId) {
                rowId = [rowId];
            } else {
                rowId = jqGrid.getSelectedRowIds();
            }
            // 获取选中行的数据
            // var rowData = jqGrid.getSelectedRowData();
            if (rowId.length === 0) {
                alert("请至少选择一条记录！");
                return;
            }
            if (!confirm("是否要删除选中的条" + rowId.length + "记录？")) {
                return;
            }
            if (rowId.length === 1) {
                $.btDelete(basePath + "/api/customer/bankType/" + rowId, {}, function () {
                    //刷新表格
                    jqGrid.refresh();
                    //重置多选项
                    jqGrid.resetSelection();
                    alert("删除成功！");
                }, function (jqXHR, textStatus, errorThrown) {
                    alert("删除失败！")
                });
            } else {
                $.post(basePath + "/api/customer/bankType/batchDelete", rowId, function () {
                    jqGrid.refresh();
                    jqGrid.resetSelection();
                    alert("删除成功！");
                }, function (jqXHR, status, error) {
                    alert("删除失败！")
                });
            }
        });

        // 查询操作
        $('button[id="query"]').click(function () {
            var data = $('form').serializeObject();
            jqGrid.search(data);
        });

    });
</script>

</body>
</html>
