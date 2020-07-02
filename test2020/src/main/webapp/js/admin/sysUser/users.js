layui.use(['form','layer','table','laytpl','cjs'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
    var cjs = layui.cjs;
    //用户列表
    var tableIns = table.render($.extend(cjs.layui.table, {
        elem: '#userList',
        url : '/admin/sysUser/page',
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'username', title: '用户名', minWidth:100, align:"center"},
            {field: 'realname', title: '真实名', minWidth:100, align:"center"},
            {field: 'createtime', title: '创建时间' ,minWidth:150, align:'center'},
            {field: 'remark', title: '备注' ,minWidth:150, align:'center'}
        ]]
    }));
    


	//添加用户
	function userInfoView(uid) {
		cjs.layer.full({
			title : "添加用户",
			type : 2,
			content : "/admin/sysUser/user?uid="+(uid?uid:""),
			success : function(layero, index) {}
		});
	}
	
	$(".createBtn").click(function(){
    	userInfoView();
    });
	$(".updateBtn").click(function() {
		var data = cjs.layui.selectRow(table, 'userListTable');
		if(data.length == 1){
			userInfoView(data[0].uid);
		}
	});
    //批量删除
    $(".delBtn").click(function(){
    	var data = cjs.layui.selectRow(table, 'userListTable');
		if(data.length == 1){
			var index = layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
				$.ajax({
					url : '/admin/sysUser/delete?uid='+data[0].uid,
					success : function(d) {
						top.layer.msg(d.message);
						if(d.code == 1){
							tableIns.reload();
			                layer.close(index);
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						layer.msg("操作失败");
						layer.close(index);
					}
				});
            })
		}
    });

});
