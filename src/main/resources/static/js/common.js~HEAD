//全选的实现
$(document).on('click','.check-all',function(){
    $(".ids").prop("checked", true);
});
$(document).on('click','.uncheck-all',function(){
    $(".ids").prop("checked", false);
});
$(function(){
	$('#top-alert').find('button').click();
});
//ajax get请求
$(document).on('click','.ajax-get',function(){
    var that = this;
    var confirm_msg = $(this).attr('data-confirm')?$(this).attr('data-confirm'):'确认要执行该操作吗?';
    if ( $(this).hasClass('confirm') ) {
        layer.confirm(confirm_msg, {
            btn: ['确认', '取消'], //按钮
            shade: false //不显示遮罩
        }, function() {
            layer.closeAll();
            ajax_get_fun(that);
        }, function() {
            layer.closeAll();
        });
    }else{
        ajax_get_fun(that);
    }
    return false;
});

function ajax_get_fun(that) {
    var target;
    if ( (target = $(that).attr('href')) || (target = $(that).attr('url')) ) {
        var flag = $(that).attr('data-flag');
        if(flag == 1){
            return '';
        }
        $(that).attr('data-flag',1);
        $.get(target).success(function(data){
        	console.log(data);
            if (data.code==1) {
                if (data.url) {
                    updateAlert(data.msg,'alert-success');
                }else{
                    updateAlert(data.msg,'alert-success');
                }
                setTimeout(function(){
                    if( $(that).hasClass('no-refresh')){
                        $('#top-alert').find('button').click();
                        $(that).attr('data-flag',0);
                    }else{
                        var datatable = $(that).attr('data-table');
                        if(datatable==1){
                            $('#top-alert').find('button').click();
                            $("#table").trigger("reloadGrid");
                        }else{
                            location.href=data.url;
                        }
                    }
                },1500);
            }else{
                updateAlert(data.msg,'alert-danger');
                setTimeout(function(){
                    if (data.url) {
                        location.href=data.url;
                    }else{
                        $('#top-alert').find('button').click();
                        $(that).attr('data-flag',0);
                    }
                },1500);
            }
        });
    }
}

//ajax post submit请求
$(document).on('click','.ajax-post',function(){
    var target,query,form;
    var target_form = $(this).attr('target-form');
    var that = this;
    var nead_confirm=false;
    var confirm_msg = $(this).attr('data-confirm')?$(this).attr('data-confirm'):'确认要执行该操作吗?';
    if( ($(this).attr('type')=='submit') || (target = $(this).attr('href')) || (target = $(this).attr('url')) ){
        form = $('.'+target_form);

        if ($(this).attr('hide-data') === 'true'){//无数据时也可以使用的功能
            form = $('.hide-data');
            query = form.serialize();
        }else if (form.get(0)==undefined){
            return false;
        }else if ( form.get(0).nodeName=='FORM' ){
            if ( $(this).hasClass('confirm') ) {
                layer.confirm(confirm_msg, {
                    btn: ['确认', '取消'], //按钮
                    shade: false //不显示遮罩
                }, function() {
                    layer.closeAll();
                    if($(this).attr('url') !== undefined){
                        target = $(this).attr('url');
                    }else{
                        target = form.get(0).action;
                    }
                    query = form.serialize();
                    ajax_post_fun(target,that,query);
                    return false;
                }, function() {
                    layer.closeAll();
                });
                return false;
            }else{
                if($(this).attr('url') !== undefined){
                    target = $(this).attr('url');
                }else{
                    target = form.get(0).action;
                }
                query = form.serialize();
            }
        }else if( form.get(0).nodeName=='INPUT' || form.get(0).nodeName=='SELECT' || form.get(0).nodeName=='TEXTAREA') {
            form.each(function(k,v){
                if(v.type=='checkbox' && v.checked==true){
                    nead_confirm = true;
                }
            })
            if ( nead_confirm && $(this).hasClass('confirm') ) {
                layer.confirm(confirm_msg, {
                    btn: ['确认', '取消'], //按钮
                    shade: false //不显示遮罩
                }, function() {
                    layer.closeAll();
                    query = form.serialize();
                    ajax_post_fun(target,that,query);
                    return false;
                }, function() {
                    layer.closeAll();
                });
                return false;
            }else{
                query = form.serialize();
            }
        }else{
            if ( $(this).hasClass('confirm') ) {
                layer.confirm(confirm_msg, {
                    btn: ['确认', '取消'], //按钮
                    shade: false //不显示遮罩
                }, function() {
                    layer.closeAll();
                    query = form.find('input,select,textarea').serialize();
                    ajax_post_fun(target,that,query);
                    return false;
                }, function() {
                    layer.closeAll();
                });
                return false;
            }else{
                query = form.find('input,select,textarea').serialize();
            }
        }
        ajax_post_fun(target,that,query);
    }
    return false;
});

function ajax_post_fun(target,that,query) {
	console.log(123);
    $(that).addClass('disabled').attr('autocomplete','off').prop('disabled',true);
    $.post(target,query).success(function(data){
        if (data.code==1) {
            if (data.url) {
                updateAlert(data.msg,'alert-success');
                // updateAlert(data.msg + ' 页面即将自动跳转~','alert-success');
            }else{
                updateAlert(data.msg ,'alert-success');
            }
            setTimeout(function(){
                $(that).removeClass('disabled').prop('disabled',false);
                if( $(that).hasClass('no-refresh')){
                    $('#top-alert').find('button').click();
                }else{
                    var datatable = $(that).attr('data-table');
                    if(datatable==1){
                        $('#top-alert').find('button').click();
                        $("#table").trigger("reloadGrid");
                    }else{
                        var datalayer = $(that).attr('data-layer');
                        if(datalayer==1){
                            parent.layer.closeAll();
                        }else{
                            if (data.url) {
                                location.href = data.url;
                            }
                            $('#top-alert').find('button').click();
                        }
                    }
                }
            },1500);
        }else{
            updateAlert(data.msg,'alert-danger');
            setTimeout(function(){
                $(that).removeClass('disabled').prop('disabled',false);
                if (data.url) {
                    location.href=data.url;
                }else{
                    $('#top-alert').find('button').click();
                }
            },1500);
        }
    });
}

/**顶部警告栏*/
var top_alert = $('#top-alert');
top_alert.find('.close').on('click', function () {
    top_alert.removeClass('block').slideUp(200);
});

window.updateAlert = function (text,c) {
    text = text||'default';
    c = c||false;
    if ( text!='default' ) {
        top_alert.find('.alert-content').text(text);
        if (top_alert.hasClass('block')) {
        } else {
            top_alert.addClass('block').slideDown(200);
        }
    } else {
        if (top_alert.hasClass('block')) {
            top_alert.removeClass('block').slideUp(200);
        }
    }
    if ( c!=false ) {
        top_alert.removeClass('alert-danger alert-warning alert-info alert-success').addClass(c);
    }
};


/* 设置表单的值 */
function formSetValue(name, value){
    var first = name.substr(0,1), input, i = 0, val;
    if(value === "") return;
    if("#" === first || "." === first){
        input = $(name);
    } else {
        input = $("[name='" + name + "']");
    }

    if(input.eq(0).is(":radio")) { //单选按钮
        input.filter("[value='" + value + "']").each(function(){this.checked = true});
    } else if(input.eq(0).is(":checkbox")) { //复选框
        if(!$.isArray(value)){
            val = new Array();
            val[0] = value;
        } else {
            val = value;
        }
        for(i = 0, len = val.length; i < len; i++){
            input.filter("[value='" + val[i] + "']").each(function(){this.checked = true});
        }
    } else {  //其他表单选项直接设置值
        input.val(value);
    }
}

/**
 * 列表页排序
 * @param _self
 */
function updateSort(_self) {
    var url = $(_self).attr('data-url');;
    var id = $(_self).attr('data-id');
    var old = $(_self).attr('data-old');
    var sort = $(_self).val();
    $.post(url,{id:id,sort:sort},function(data){
        if(data.code){
            $("#table").trigger("reloadGrid");
        }else{
            $(_self).val(old);
            updateAlert(data.msg,'alert-danger');
            setTimeout(function(){
                if (data.url) {
                    location.href=data.url;
                }else{
                    $('#top-alert').find('button').click();
                }
            },1500);
        }
    });
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//搜索
$('#search-btn').on('click', function() {
    var self = $("#form-search");
    var url = self.attr('action');
    if(url){
        var pdata = self.serializeObject();
        $("#table").jqGrid('setGridParam', {
            url: url,
            postData:pdata,
            page: 1
        }).trigger("reloadGrid");
    }
});

//清除搜索
$('#search-clear').on('click',function () {
    $("#form-search")[0].reset();
    var url = $("#form-search").attr('action');
    if(url){
        var pdata = $("#form-search").serializeObject();
        $("#table").jqGrid('setGridParam',{url:url,postData:pdata,page:1}).trigger("reloadGrid");
    }
});

/**
 * 输入限制，只可以输入小数点和数字且小数点后只可以输入两个数字
 * 一般用于onkeyup 和 onpaste方法
 * 例：onkeyup="clearNoNum(this)"
 * @param obj
 */
function clearNoNum(obj){
    if(obj.value.substr(0,1)=='.'){
        obj.value = '';
    }
    obj.value = obj.value.replace(/[^\d.]/g,"");
    obj.value = obj.value.replace(/\.{2,}/g,".");
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
    if(obj.value.indexOf(".")< 0 && obj.value !=""){
        obj.value= parseFloat(obj.value);
    }
}

/**
 * 输入限制，只可以输入数字
 * 一般用于onkeyup 和 onpaste方法
 * 例：onkeyup="clearNoNum(this)"
 * @param obj
 */
function clearNoNum1(obj){
    if(obj.value.substr(0,1)=='.'){
        obj.value = '';
    }
    obj.value = obj.value.replace(/[^\d.]/g,"");
    obj.value = obj.value.replace(/\./g,"");
}

/**
 * 输入限制，只可以输入小数点和数字且小数点后只可以输入一个数字
 * 一般用于onkeyup 和 onpaste方法
 * 例：onkeyup="clearNoNum(this)"
 * @param obj
 */
function clearNoNum2(obj){
    if(obj.value.substr(0,1)=='.'){
        obj.value = '';
    }
    obj.value = obj.value.replace(/[^\d.]/g,"");
    obj.value = obj.value.replace(/\.{2,}/g,".");
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d).*$/,'$1$2.$3');
    if(obj.value.indexOf(".")< 0 && obj.value !=""){
        obj.value= parseFloat(obj.value);
    }
}

/**
 * 清除“数字”以外的字符
 * 一般用于onkeyup 和 onpaste方法
 * 例：onkeyup="clearNoNum(this)"
 * @param obj
 */
function clearNoNum3(obj){
    obj.value = obj.value.replace(/[^\d]/g,"");  //清除“数字”以外的字符
}

//清除特殊字符
function stripscript(obj) {
    var s = $(obj).val();
    var pattern = new RegExp("[`~!@#$%+^&*()=|{}':;'\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
    var rs = "";
    for (var i = 0; i < s.length; i++) {
        rs = rs + s.substr(i, 1).replace(pattern, '');
    }
    $(obj).val(rs);
}

function layer_open(_self,flag){
    var url = $(_self).attr('url');
    var title = $(_self).attr('data-title');
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: 0.3,
        maxmin: true,
        area: ['90%', '90%'],
        content: url,
        end: function () {
            if(flag==1){
                $("#table").trigger("reloadGrid");
            }else if(flag==2){
                window.location.reload();
            }
        }
    });
}

function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURI(r[2]);
    }
    return null;
}

$.ajaxSetup( {      
    //设置ajax请求结束后的执行动作      
    complete : function(XMLHttpRequest, textStatus) {   
        // 通过XMLHttpRequest取得响应头，REDIRECT      
        var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向    
        if (redirect == "REDIRECT") {  
            var win = window;      
            while (win != win.top){    
                win = win.top;    
            }  
            //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求    
//            layerAlert(layer,"您的登录信息已超时，请重新登录<p class='ps'>ps:在客户端闲置10分钟后系统会自动登出</p>",'登录超时提示',5,'',0,function(){  
                win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");  
//            })  
        }else if(XMLHttpRequest.getResponseHeader("no_privilege") == "no_privilege"){
        	layer.alert("对不起,您没有此权限!",{
        		  icon: 2,
        		  skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
        		});
        }  
    },  
});   