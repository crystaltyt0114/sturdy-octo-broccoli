layui.use([ 'layer', 'transfer', 'form', 'cjs'], function() {
	var layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		transfer = layui.transfer,
		form = layui.form;
	var cjs = layui.cjs;
	
	form.on("submit(createOrUpdateBtn)", function(data) {
		submit();
		return false;
	});
	
	function submit(){
		var uid = $("#hid_uid").val();
		$.ajax({
			url : '/sys/user/updatePassword',
			data : function() {
				return $("form").serialize();
			}(),
			success: function(d) {
				top.layer.msg(d.message);
				if (d.code == 1) {
					top.window.location.href = '/sys/logout';
				}
			}
		});
	}

});