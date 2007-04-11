function getConfigDetails() {
	var databaseElem = $F("database");
	var fileElem = $F("file");
	var params = "database=" + (databaseElem != null ? "on" : "off") +
		"&file=" + (fileElem != null ? "on" : "off");

	new Ajax.Updater("configs", "/websterm_client/Configuration.action",
		{parameters:params});
}