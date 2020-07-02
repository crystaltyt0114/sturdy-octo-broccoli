// 只能在最顶层的页面显示登录页面
if(window!==top.window) {
	top.window.location.href = window.location.href;
}
layui.use([ 'form', 'layer', 'jquery' ], function() {
	//表单输入效果
	$(".loginBody .input-item").click(function(e) {
		e.stopPropagation();
		$(this).addClass("layui-input-focus").find(".layui-input").focus();
	});
	$(".loginBody .layui-form-item .layui-input").focus(function() {
		$(this).parent().addClass("layui-input-focus");
	});
	$(".loginBody .layui-form-item .layui-input").blur(function() {
		$(this).parent().removeClass("layui-input-focus");
		if ($(this).val() != '') {
			$(this).parent().addClass("layui-input-active");
		} else {
			$(this).parent().removeClass("layui-input-active");
		}
	});
});

//登录操作
function login() {
	//提交
	$.ajax({
		url : "/admin/login",
		type : "post",
		data : $("form").serialize(),
		dataType : "json",
		success : function(data) {
			if (data.code == 1) {
				location.href = "/admin/main";
			} else {
				changeCode();
				layer.msg(data.message, {time: 3000, icon:5});
			}
		}
	});
	return false;
}

function changeCode() {
	document.getElementById("validateCode").src = "/admin/getvalidCode?tockenId=QWERTYUISDFGHJEW" + Math.random();
}