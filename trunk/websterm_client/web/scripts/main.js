function getDbConfigDetails() {
	var databaseElem = $F("database");
	var params = "database=" + (databaseElem != null ? "on" : "off");
	new Ajax.Updater("dbConfigs", "/websterm_client/Configuration.action?getDbConfigDetails=",
		{parameters:params});
}

function getFileConfigDetails() {
	var fileElem = $F("file");
	var params = "file=" + (fileElem != null ? "on" : "off");
	new Ajax.Updater("fileConfigs", "/websterm_client/Configuration.action?getFileConfigDetails=",
		{parameters:params});
}

function fetchDBFields() {
	var params = Form.serialize("dbConfigForm");
	params = params.replace(/configureDatabase=/g, "");
	new Ajax.Updater("fieldsContainer", "/websterm_client/Configuration.action",
		{parameters:params});
}

function submitForm(msgArea, form) {
	var params = Form.serialize(form);
	new Ajax.Updater(msgArea, form.action, {parameters:params});
}

function toggleEngine(status) {
	var url = "/websterm_client/Admin.action?toggleEngine=&status=" + status;
	new Ajax.Updater("statusPanel", url);
}