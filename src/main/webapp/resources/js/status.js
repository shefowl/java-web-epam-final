window.onload= function() {
	var btn = document.getElementById('buttonMenu');
	if (btn) btn.addEventListener("click", showMenuReference, false);
	var st = document.getElementById('status');
	if (st) st.addEventListener("click", setStatusReference, false);
}

var setStatusReference = function setStatus() {
	var status = document.getElementById("status");
	var form = document.getElementById("formFree");
	if(status.className == "busy_status")
	{
		status.value="I'm free";
		status.className="free_status";
		form.action ="driver?action=free";
	}
	else {
		status.value="I'm busy";	
		status.className="busy_status"
		form.action ="driver?action=busy";
	}
}

	var showMenuReference = function showMenu() {
	var menu = document.getElementById("menu");
	if(menu.className == "menu_active")
	menu.className="menu";
	else {
		menu.className="menu_active";	
	}

}