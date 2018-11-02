(function(window){

    var addForm = $('#testcaseForm');
    var DataTable;
    var uploadForm = $('#uploadForm');

    //时间选择器
    var createTime =  $("#query_createTime").daterangepicker({
        format:'YYYY-MM-DD',//格式化日期
        showDropdowns:true,//显示年与月的选择框
        minDate:"2010-01-01",//最小日期
        maxDate:"2020-12-31",//最大日期
        applyClass: 'btn-success',//应用按钮class
        cancelClass: 'btn-warning',//取消按钮class
        separator:"/",
        ranges: {
            '今天': [moment(), moment()],
            '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            '一周内': [moment().subtract(7, 'days'), moment()],
            '30天内': [moment().subtract(30, 'days'), moment()],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上个月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        locale: {
            applyLabel: '保存',
            cancelLabel: '取消',
            fromLabel: '起始时间',
            toLabel: '结束时间',
            customRangeLabel: '选择日期',
            daysOfWeek: ['周日','周一', '周二', '周三', '周四', '周五', '周六'],
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        },
    });
    function formatDate(date){
        date = new Date(date);
        var y=date.getFullYear();
        var m=date.getMonth()+1;
        var d=date.getDate();
        var h=date.getHours();
        var m1=date.getMinutes();
        var s=date.getSeconds();
        m = m<10?("0"+m):m;
        d = d<10?("0"+d):d;
        return y+"-"+m+"-"+d+" "+h+":"+m1+":"+s;
    }

    //初始化文件上传插件
    function initFileUpload(){
        $("#upload_package").fileinput({
            language: 'zh',                                 //中文
            uploadUrl:"/package/upload",
            showPreview: false,				//不展前预览
            showUpload: false,				//不显示上传按钮
            showCaption: true,				//显示文字表述
            uploadAsync:false,                             //采用同步上传
            removeFromPreviewOnError:true,                 //当文件不符合规则，就不显示预览
            maxFileCount: 1,
            maxFileSize: 0,                          //单位为kb，如果为0表示不限制文件大小
            allowedFileExtensions: ["apk", "ipa"],
            uploadExtraData:function(){
                var data={
                    "businessType":$("#upload_businessType").val(),
                    "appType":$("#upload_appType").val(),
                    "appRelease":$("#upload_appRelease").val(),
                    "appEnv":$("#upload_appEnv").val(),
                    "remark":$("#upload_remark").val(),
                };
                return data;

            }
        }).on("filebatchuploadsuccess", function(event, data) {
            var response = data.response;
            if(response.code=='200'){
                $("#upload_btn_close").click();
                DataTable.ajax.reload();
            }
        });
    }

    function initBindEvents(){

        DataTable = initDataTables('package-table');
        $("#create_case_btn").click(function () {
            //清除缓存
            formInit();
            var data={
                "businessType":$("#add_businessType").val(),
                "requirementName":$("#add_requirementName").val(),
                "testcaseTurn":$("#add_testcaseTurn").val(),
                "testcaseName":$("#add_testcaseName").val(),
                "testcaseLevel":$("#add_testcaseLevel").val(),
                "testcaseStep":$("#add_testcaseStep").val(),
                "testcaseExpectedResult":$("#add_testcaseExpectedResult").val(),
                "testcaseOwner":$("#add_testcaseOwner").val()
            };
            $.ajax({
                url:"/testCase/save",
                type:"post",
                dataType:"json",
                cache: false,
                data :data,
                success: function (datas){
                    if(datas.code == HttpUtil.success_code){
                        // addForm.data('bootstrapValidator').resetForm();
                        addForm[0].reset();
                        $("#close").click();
                        DataTable.ajax.reload();//刷新
                    }
                }
            });
        })
        initFileUpload();

        //点击上传
        $("#package-save-btn").click(function(){
            formInit();
            if($("#upload_package").fileinput("getFilesCount")<=0){
                alert("请选择上传文件!");
                return
            }
            $('#upload_package').fileinput('upload');
        })

        //选择取消按钮时
        $("#query_createTime").on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });

        $("#find").click(function(){
            DataTable.ajax.reload();
        });


    }

    var columns = [
        { "data": null},
        { "data": "id","visible":false},//visible 隐藏或显示
        { "data": "businessType"},
        { "data": "requirementName"},
        { "data": "testcaseTurn" },
        { "data": "testcaseName" },
        { "data": "testcaseLevel" },
        { "data": "testcaseStep" },
        { "data": "testcaseExpectedResult" },
        { "data": "testcaseActualResult" },
        { "data": "testcaseOwner" },
        { "data": "remark" },
        { "data": null,"render":function(data,type,row,meta){
                if(null!=data){
                    var buttons = '';
                    buttons += $('#button-download').html();
                    buttons += $('#button-code').html();
                    buttons += $('#button-del').html();
                    return buttons.replace(/#id/g, row.id);
                }
            }},
    ];
    function initDataTables(tableId) {
        return $('#' + tableId + '').DataTable({
            bDestroy: true,
            searching: false,
            processing: true,
            serverSide: true,
            ordering: false, //排序
            ajax:{
                "url":"/testCase/search",
                "type": "POST",
                "dataType":"json",
                "data":function(d){
                    d.businessType=$("#query_businessType").val();
                    d.testcaseTurn=$("#query_testcaseTurn").val();
                    d.testcaseLevel=$("#query_testcaseLevel").val();
                    d.testcaseActualResult=$("#query_testcaseActualResult").val();
                    d.testcaseOwner=$("#query_testcaseOwner").val();
                    d.requirementName=$("#query_requirementName").val();
                    d.dateString=$("#query_createTime").val();
                }
            },
            columns: columns,
            drawCallback: function(settings){//设置第一列为自增列
                this.api().column(0).nodes().each(function(cell,i){
                    cell.innerHTML =  i + 1;
                })
            },
            columnDefs:[
                {className: "dt-body-center", "targets": "_all"},
                // {
                //     "targets": [11,12],
                //     "render":function(data,type,row,meta){
                //         if(data == null){
                //             return "无登录";
                //         }
                //         return TimeObjectUtil.formatterDateTime(new Date(data))
                //     }
                // }
            ],
            language: {
                lengthMenu: "每页 _MENU_ 条记录",//下拉框文字
                info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页 ,共 _TOTAL_ 项)",//左下角显示文字
                infoEmpty: "",//当查询没有数据时左下角显示文字
                sEmptyTable: "没有数据..",//当查询没有数据时表格中间显示文字
                paginate: {                          //分页
                    first: "首页",
                    last: "尾页",
                    next: "下一页",
                    previous: "上一页"
                },
                decimal:","
            }
        });

    }



    function formInit(){
        $(".has-error").each(function(i,d){
            $(d).removeClass("has-error has-feedback");
        })
        $("li[name=ErrorTip]").each(function(i,d){
            $(d).html("");
        })
    }

    //下载文件
    function downloadFile(obj) {
        var downloadUrl;
        var datas={id:obj};
        $.ajax({
            url:"/package/getDownloadUrl",
            type:"post",
            dataType: "json",
            cache: false,
            async: false,
            data: datas,
            success: function (result) {
                if (result != "" && result != null) {
                    downloadUrl=result;
            }
            }
        });
        window.location.href = downloadUrl;
    }
    //获取二维码
    function showImg(obj){
        var datas={id:obj};
        $.ajax({
            url:"/package/getImageUrl",
            type:"post",
            dataType: "json",
            cache: false,
            async: false,
            data: datas,
            success: function (data) {
                if(data == null){
                    toastmessage("没有生成对应的二维码！")
                }else {
                    $("#showImage").css({"display": "block"}).attr("src",data);
                }

            }
        });
    }
    function hideImg() {
        $("#showImage").css({"display": "none"});
    }
    //刪除
    function deleteTestcase(id){
        //status当前状态,1正常,0锁定
        // var message = lock == 1?"是否要锁定用户?":"是否要解锁用户?";
        var message = "是否要删除当前case？";
        var data = {};
        BootstrapDialog.confirm({
            title: '删除用例',
            message: message,
            type: BootstrapDialog.TYPE_WARNING,
            closable: true,
            btnCancelLabel: '取消',
            btnOKLabel: '确认',
            btnOKClass: 'btn-warning',
            callback: function (result) {
                if (result) {
                    console.log(result);
                    data = {"id": id}
                    $.ajax({
                        url:"/testCase/del",
                        type: "post",
                        dataType: "json",
                        cache: false,
                        async: false,
                        data: data,
                        success: function (d) {
                            if (d.code == HttpUtil.success_code) {
                                //重新加载下用户表格
                                DataTable.ajax.reload(null, false);
                            }
                            if (d.code == HttpUtil.error_code) {
                                $().toastmessage('showErrorToast', d.msg);
                            }
                        }
                    })
                }
            }
        })
    }
    //上传附件弹出框
    function uploadFileDialog(obj){
        // updateForm.data('bootstrapValidator').resetForm();
        uploadForm[0].reset();
        formInit();
    }

    //需要暴露到global域的API
    window.page ={
        init:function () {

            //初始化各种事件绑定
            console.log("111");
            initBindEvents();

        }, uploadFileDialog:uploadFileDialog,deleteTestcase:deleteTestcase,downloadFile:downloadFile,showImg:showImg,hideImg:hideImg
    }


})(window);



