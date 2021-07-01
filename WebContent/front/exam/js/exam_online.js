

//选择答案（单选singoselect）ss
function click_answer(n){

	for(var i=0;i<=3;i++){
		if(i != n ){
			this.parentNode.children[i].style.color = "#888888";
		}else{
			this.parentNode.children[i].style.color = "#65a2fd";
		}
	}
}

