function soloLetras(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /[A-Za-zñÑ\s] [.q´\w]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
	
	
}
function numYletras(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /[nÑ\s|\w]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
	
}

