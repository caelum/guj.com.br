function alignHomeBoxes() {
	var boxHeight = $("#home .forum").innerHeight();
	var newsHeight = $("#home .news").innerHeight();
	var articlesHeight = $("#home .articles").innerHeight();
	
	if (boxHeight < newsHeight) {
		boxHeight = newsHeight;
	}
	
	if (boxHeight < articlesHeight) {
		boxHeight = articlesHeight;
	}
	
	$("#home .forum .spiffyfg").css("height", boxHeight);
	$("#home .news .spiffyfg").css("height", boxHeight);
	$("#home .articles .spiffyfg").css("height", boxHeight);
	
	boxHeight = $("#home .posts").innerHeight();
	var infoqHeight = $("#home .infoq").innerHeight();
	
	if (boxHeight < infoqHeight) {
		boxHeight = infoqHeight;
	}
	
	$("#home .posts .spiffyfg").css("height", boxHeight);
	$("#home .infoq .spiffyfg").css("height", boxHeight);
}