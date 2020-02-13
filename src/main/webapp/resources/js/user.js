window.onload= function() {
	var btn = document.getElementById('buttonMenu');
	if (btn) btn.addEventListener("click", showMenuReference, false);
}

	var showMenuReference = function showMenu() {
	var menu = document.getElementById("menu");
	if(menu.className == "menu_active")
	menu.className="menu";
	else {
		menu.className="menu_active";	
	}

}