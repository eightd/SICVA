function soloLetras(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /[A-Za-zñÑ-áéíóúÁÉÍÓÚ-,\s]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);

}
function numYletras(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /[nÑ-áéíóúÁÉÍÓÚ\s|\w]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);

}

function numeros(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
    patron = /[1234567890]/;  
    te = String.fromCharCode(tecla);
    return patron.test(te); 
}
function direccion(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /[nÑ-áéíóúÁÉÍÓÚ\s|#\s|\w]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);

}