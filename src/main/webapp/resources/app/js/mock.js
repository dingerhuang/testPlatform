(function(window){


    var DataTable;
    var addForm = $('#mockUrlForm');
    var updateForm = $('#updateMockForm');

    //时间选择器
    var createTime =  $("#createTime").daterangepicker({
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
    function initBindEvents(){

        DataTable = initDataTables('mock-table');

        //保存用户
        $("#create-mockUrl-btn").click(function(){
            //清除
            formInit();
            var data = {
                "mockUrl": $("#add_mockUrl").val(),
                "timeOut": $("#add_timeOut").val(),
                "remark": $("#add_remark").val(),
                "project": $("#add_project").val(),
                "mockResponse": $("#add_mockResponse").val(),
                "mockPara": $("#add_mockPara").val()
            };

            $.ajax({
                url:"/mock/save",
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

        //修改用户
        $("#update-mock-btn").click(function(){
            formInit();
            var data =  {
                "mockPara": $("#update_mockPara").val(),
                "mockResponse": $("#update_mockResponse").val(),
                "id": $("#mockId").val(),
                "remark": $("#update_remark").val(),
                "timeOut": $("#update_timeOut").val()
            };
            $.ajax({
                url:"/mock/update",
                type:"post",
                dataType:"json",
                cache: false,
                data :data,
                success: function (datas){
                    if(datas.code == HttpUtil.success_code){
                        $("#update_close").click();
                        DataTable.ajax.reload();//刷新
                    }
                }
            })
        })

        //选择取消按钮时
        $("#createTime").on('cancel.daterangepicker', function(ev, picker) {
            $(this).val('');
        });

        $("#find").click(function(){
            DataTable.ajax.reload();
        });


    }

    var columns = [
        { "data": null},
        { "data": "id","visible":false},//visible 隐藏或显示
        { "data": "mockUrl"},
        { "data": "mockPara"},
        { "data": "mockResponse" },
        { "data": "timeOut" },
        { "data": "project", "render":function(data,type,row,meta) {
            if(data == '1'){
                return "小凡";
            }else if(data == '2'){
                return "个贷";
            }else {
                return "null";
            };
            }
        },
        {"data":"remark"},
        { "data": "lastModifyUser"},
        { "data": "updateTime","render":function (data,type,row,meta) {
                if(data!=null){
                    return formatDate(data);
                }else {
                    return "";
                }

            }
        },
        { "data": null,"render":function(data,type,row,meta){
                if(null!=data){
                    var buttons = '';
                    buttons += $('#button-1').html();
                    buttons += $('#button-2').html();
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
                "url":"/mock/search",
                "type": "POST",
                "dataType":"json",
                "data":function(d){
                    d.mockUrl=$("#mockUrl").val();
                    d.dateString=$("#createTime").val();
                    d.project=$("#project").val();
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

    //刪除
    function deleteMockUrl(id){
        //status当前状态,1正常,0锁定
        // var message = lock == 1?"是否要锁定用户?":"是否要解锁用户?";
        var message = "是否要刪除當前URL";
        var data = {};
        BootstrapDialog.confirm({
            title: '删除URL',
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
                        url:"/mock/del",
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
    //修改用户弹出框
    function updateMockDialog(obj){
        // updateForm.data('bootstrapValidator').resetForm();
        updateForm[0].reset();
        formInit();
        $.ajax({
            url:"/mock/entity",
            type:"post",
            dataType:"json",
            cache: false,
            async: false,
            data :{"id":obj},
            success: function (datas){
                if(datas.code == HttpUtil.success_code){
                    var result = datas.result;
                    updateForm.find("#mockId").val(result.id);
                    updateForm.find("#update_mockUrl").val(result.mockUrl);
                    updateForm.find("#update_mockPara").val(result.mockPara);
                    updateForm.find("#update_mockResponse").val(result.mockResponse);
                    updateForm.find("#update_remark").val(result.remark);
                    updateForm.find("#update_timeOut").val(result.timeOut);
                    updateForm.find("#update_job").val(result.job);


                }
            }
        })
    }

//需要暴露到global域的API
    window.page ={
        init:function () {

            //初始化各种事件绑定
            console.log("111");
            initBindEvents();

        }, updateMockDialog:updateMockDialog,deleteMockUrl:deleteMockUrl
    }


})(window);



