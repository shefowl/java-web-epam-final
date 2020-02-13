window.onload= function() {
	var btn = document.getElementById('buttonMenu');
	if (btn) btn.addEventListener("click", showMenuReference, false);

	var log = document.getElementById('log');
	if (log) log.addEventListener("click", showLoginFormReference, false);

	var passw = document.getElementById('passw');
	if (passw) passw.addEventListener("click", showPasswordFormReference, false);
}


	var showLoginFormReference = function showLoginForm() {
	var style = document.getElementById("login_form").style;
	style.display = (style.display == 'block') ? 'none' : 'block';

}

	var showPasswordFormReference = function showPasswordForm() {
	var style = document.getElementById("password_form").style;
	style.display = (style.display == 'block') ? 'none' : 'block';
	}
	

	var showMenuReference = function showMenu() {
	var menu = document.getElementById("menu");
	if(menu.className == "menu_active")
	menu.className="menu";
	else {
		menu.className="menu_active";	
	}

}