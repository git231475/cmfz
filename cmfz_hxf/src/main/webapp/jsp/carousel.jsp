<%@ page pageEncoding="utf-8" isELIgnored="false" contentType="text/html; utf_8" language="java" %>
    <div class="page-header">
        <h1>轮播图管理</h1>
    </div>
<script>
    $(function(){
        $("#carouselTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/carousel/queryAll",
            datatype:"json",
            colNames:["编号","主题","图片","状态","上传时间"],
            colModel:[
                {name:"id"},
                {name: "title",editable:true},
                {name:"imgPath",editable:true,edittype:"file",formatter:function (cellvalue,options,rowObject) {
                    return "<img style='width:50px;height:50px'src='${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'>";
                    }},
                {name:"status",editable:true},
                {name:"date",editable:true,edittype:"date"}],
            pager:"carouselPager",
            rowNum:3,
            rowList:[3,5,8],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/carousel/edit",
            height:200,
            multiselect:true,
            rownumbers:true,
        }).jqGrid("navGrid","#carouselPager",{search:false},
            {
            //修改
                closeAfterEdit:true,

            },
            {
                //添加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/carousel/upload",
                        fileElementId:"imgPath",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#carouselTable").trigger("reloadGrid");
                        }
                    })
                    return"[true]";
                    
                }
            })
    })
</script>
<table id="carouselTable"></table>
<div id="carouselPager"></div>