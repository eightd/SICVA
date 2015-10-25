function valid(form) {
	var name = document.forms['form'].elements['form:nombre'].value;
	var firstname = document.forms['form'].elements['form:apater'].value;
	var secondname = document.forms['form'].elements['form:amater'].value;
	var claveDeUsuario;
	var username;
	var random = Math.floor(Math.random() * (100 - 0)) + 0;

	firstname = firstname.replace(' ', '');
	firstname = firstname.replace(' ', '');
	claveDeUsuario = name.charAt(0);
	claveDeUsuario = claveDeUsuario + firstname;
	claveDeUsuario = claveDeUsuario + secondname.charAt(0);
	claveDeUsuario = claveDeUsuario + String(random);
	username = omitirAcentos(claveDeUsuario)
	
	document.forms['form'].elements['form:usua'].value = username
			.toUpperCase();
	document.forms['form'].elements['form:cont'].value = username
			.toUpperCase();
	  
}
function omitirAcentos(text) {
	var acentos = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÑñÇç";
	var original = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuunncc";
	
	for (var i = 0; i < acentos.length; i++) {
		text = text.replace(acentos.charAt(i), original.charAt(i));
	}
	return text;
}

function chequea(campo,boton){
	if (campo.value != ""){
		boton.disabled=false;
	} else {
		boton.disabled=true;
	}
}


