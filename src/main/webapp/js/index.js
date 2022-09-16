const changeButton = () => {
	
	var button = document.getElementById("button1");
	var checkbox = document.getElementById("check");
	
	if (checkbox.checked) {
		button.disabled = false;
	} else {
		button.disabled = true;
	}
}


