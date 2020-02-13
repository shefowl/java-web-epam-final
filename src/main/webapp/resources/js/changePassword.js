window.onload= function() {
	var el = document.getElementById('ss');
	var element;
	if (el) el.addEventListener("click", checkFunctionReference, false);
}

	var checkFunctionReference = function checkData() {
	var passw = document.getElementById("password");
	if(((password.value).length > 3))
	{
		element= document.getElementById('valid_message');
 		element.innerHTML = "";
	}
	else {
		element= document.getElementById('valid_message');
 		element.innerHTML = "*Password must to be at least 4 symbols";
	}
}