<%@ page pageEncoding="utf-8" isELIgnored="false" contentType="text/html; utf_8" language="java" %>
    <div class="page-header">
        <h1>专辑管理</h1>
    </div>
<script>
    $(function(){
        $("#albumTable").jqGrid({
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/album/queryAll",
            datatype:"json",
            colNames:["编号","专辑名称","封面","章节数目","专辑得分","专辑作者","播音员","专辑简介","出版时间"],
            colModel:[
                {name:"id"},
                {name: "title",editable:true},
                {name:"cover",editable:true,edittype:"file",formatter:function (cellvalue,options,rowObject) {
                    return "<img style='width:50px;height:50px'src='${pageContext.request.contextPath}/albumPic/"+cellvalue+"'>";
                    }},
                {name:"count",editable:true},
                {name:"score"},
                {name:"author",editable:true},
                {name:"broadcast",editable:true},
                {name:"brief",editable:true},
                {name:"publishTime",editable:true,edittype:"date"}],
            pager:"albumPager",
            rowNum:3,
            rowList:[3,5,8],
            viewrecords:true,
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/album/edit",
            height:200,
            multiselect:true,
            rownumbers:true,
            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        url :"${pageContext.request.contextPath}/chapter/queryAll?albumId=" + row_id,
                        styleUI:"Bootstrap",
                        autowidth:true,
                        datatype : "json",
                        colNames : [ '编号', '专辑编号', '主题', '存储','下载路径','上传时间','操作' ],
                        colModel : [
                            {name : "id"},
                            {name : "albumId"},
                            {name : "title",editable:true},
                            {name : "size"},
                            {name : "downPath",editable:true,edittype:"file"},
                            {name : "uploadTime",editable:true,edittype:"date"},
                            {name : "downPath",formatter:function (cellvalue,options,rowObject){
                                return "<a class=\"btn btn-primary\" href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"
                                }}
                        ],
                        editurl:"${pageContext.request.contextPath}/chapter/edit?albumId=" + row_id,
                        viewrecords:true,
                        multiselect:true,
                        rowNum : 20,
                        pager : pager_id,
                        sortname : 'num',
                        sortorder : "asc",
                        height : '100%'
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {
                        edit : false,
                        search : false,
                        del : false
                    },{ //修改
                        closeAfterEdit:true,},
                    {
                        //添加
                        closeAfterAdd:true,
                        afterSubmit:function (response) {
                            $.ajaxFileUpload({
                                url:"${pageContext.request.contextPath}/chapter/upload",
                                fileElementId:"downPath",
                                data:{"id":response.responseText},
                                type:"post",
                                success:function () {
                                    $("#"+subgrid_table_id+"").trigger("reloadGrid");
                                }
                            })
                            return"[true]";

                        }

                    }
                    );
            },
        }).jqGrid("navGrid","#albumPager",{search:false,edit:false},
            {
            //修改
                closeAfterEdit:true,

            },
            {
                //添加
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/upload",
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
<table id="albumTable"></table>
<div id="albumPager"></div>