function iFrameHeight(ifr3) {
		//获取自动适应高度的是iframe
		var ifm = document.getElementById(ifr3);
		//获取iframe里面的页面文档
		var subWeb = document.frames ?
				document.frames[ifr3].document : ifm.contentDocument;
		if (ifm != null && subWeb != null && subWeb.body.scrollHeight) {
				ifm.height = 0;
				ifm.height = subWeb.body.scrollHeight;
		}
	} 
//获取URL值
function getValueByKey(key) {
	// 获取到url,并且转为string
	var address = location.toString();
	// 截取到要找的key的位置
	var index = address.indexOf(key, address.indexOf("?"));
	// 找到要查找的值的结束的位置,最后一个没有"&"就设置为最大长度
	var index2 = address.indexOf("&", index);
	index2 = index2 == -1 ? address.length : index2;
	// 从开始位置,截取到结束位置的数据,就是我们要的
	var value = address.substring(index + key.length + 1, index2);
	return value;
}


function parentHeight(frameId,parentId) {
	var p = parent.document.getElementById(parentId);
	var my = document.getElementById(frameId);
	if (p && my) {
			p.height = parseInt(my.height) + 30;
	}
}