window.onload = function() {
	$("#carno").on("focus", function() {
		var carno = $("#carno").val();
		if(carno == "输入车牌") {
			$("#carno").val("");
		}
	});
	$(".next_step").on("click", function() {
		var carno = $("#carno").val().toUpperCase();
		if(carno == "输入车牌" || carno == "") {
			layer.open({
				content: '请输入车牌',
				skin: 'msg',
				time: 2
			});
		} else if(check_carno(carno) == false) {
			layer.open({
				content: '请输入正确车牌',
				skin: 'msg',
				time: 2
			});
		} else {
			$.ajax({
				type: "post",
				url: location.href,
				async: false,
				data: {
					"carno": carno
				},
				dataType: "html",
				success: function(data) {
					if(data == 'success') {
						layer.open({
							content: '绑定成功',
							skin: 'msg',
							time: 2
						});
						setTimeout(function(){
							window.location.href="index.php";
						},2000);
					} else {
						layer.open({
							content: '绑定失败',
							skin: 'msg',
							time: 2
						});
					}
				}
			});
		}
	});
}

function check_carno(carno) {
	var re = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
	if(carno.search(re) == -1) {
		return false;
	} else {
		return true;
	}
}