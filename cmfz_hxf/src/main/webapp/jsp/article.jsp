<%@ page pageEncoding="utf-8" isELIgnored="false" contentType="text/html; utf_8" language="java" %>
    <div class="page-header" >
        <h1>文章管理</h1>
    </div>
<script>
    $(function () {
        $("#articleTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/article/queryAll",
            editurl:"${pageContext.request.contextPath}/article/edit",
            datatype:"json",
            colNames:["编号","上师","主题","内容","发布时间"],
            colModel:[
                {name:"id"},
                {name: "guruId",editable:true},
                {name:"title",editable:true},
                {name:"content",editable:true},
                {name:"publishTime",editable:true}],
            pager:"articlePager",
            rowNum:3,
            rowList:[3,5,8],
            viewrecords:true,
            autowidth:true,
            height:200,
            multiselect:true,
            rownumbers:true,
        }).jqGrid("navGrid","#articlePage",{search:false},
            {
                //修改
                closeAfterEdit:true,

            },
            {
                //添加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/article/upload",
                        fileElementId:"cover",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#albumTable").trigger("reloadGrid");
                        }
                    })
                    return"[true]";

                }
            })

    })
</script>
<table id="articleTable"></table>
<div id="articlePage"></div>