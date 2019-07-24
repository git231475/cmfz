<%@ page pageEncoding="utf-8" isELIgnored="false" contentType="text/html; utf_8" language="java" %>
    <div class="page-header">
        <h1>用户管理</h1>
    </div>
<script>
    $(function () {
        $("#userTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/user/queryAll",
            datatype:"json",
            colNames:['用户编号','手机号','密码','盐','法号','省','市','性别','个性签名','头像','状态','注册时间'],
            colModel:[
                {name:"id"},
                {name:"phone",editable:true},
                {name:"password",editable:true},
                {name:"salt"},
                {name:"dharmaName",editable:true},
                {name:"province",editable:true},
                {name:"city",editable:true},
                {name:"gender",editable:true},
                {name:"personalSign",editable:true},
                {name:"profile",editable:true,edittype:"file",formatter:function (cellvalue,options,rowObject) {
                        return "<img style='width:50px;height:50px'src='${pageContext.request.contextPath}/userPic/"+cellvalue+"'>";
                    }},
                {name:"status",editable:true},
                {name:"registTime",editable:true,edittype:"date"},
                ],
            pager:"userPager",
            rowNum:3,
            rowList:[3,5,8],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/user/edit",
            height:200,
            multiselect:true,
            rownumbers:true,

        }).jqGrid("navGrid","#userPager",{search:false,edit:true,del:false},
            {
                //修改
                closeAfterEdit:true,

            },
            {
                //添加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        fileElementId:"profile",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#userTable").trigger("reloadGrid");
                        }
                    })
                    return"[true]";

                }
            })
    })

</script>
<table id="userTable"></table>
<div id="userPager"></div>