window.onload= function() {
	var el = document.getElementById('ss');
	var element;
	if (el) el.addEventListener("click", checkFunctionReference, false);
}

	var checkFunctionReference = function checkData() {
	var login = document.getElementById("name");
	var passw = document.getElementById("password");
	if(((login.value).length > 3)||((password.value).length > 3))
	{
		element= document.getElementById('valid_message');
 		element.innerHTML = "";
	}
	else {
		element= document.getElementById('valid_message');
 		element.innerHTML = "*Incorrect login or password";
	}
}