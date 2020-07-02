layui.use([ 'form', 'layer', 'cjs'], function() {
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
	$ = layui.jquery;
	var cjs = layui.cjs;
	var index = null;
	form.on("submit(createOrUpdateUser)", function(data) {
		//弹出loading
		index = cjs.layer.submit();
		submit();
		return false;
	});
	
	function submit(){
		var uid = $("#hid_uid").val();
		var url = uid?'/admin/sysUser/update':'/admin/sysUser/create';
		// 实际使用时的提交信息
		$.ajax({
			url : url,
			type : 'post',
			data : function() {
				return $("form").serialize();
			}(),
			success : function(d) {
				top.layer.msg(d.message);
				if(d.code == 1){
					top.layer.close(index);
					layer.closeAll("iframe");
					//刷新父页面
					parent.location.reload();
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				top.layer.msg("操作失败");
			}
		});
	}
})