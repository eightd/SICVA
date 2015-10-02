function valid(form) {
			var name = document.forms['form'].elements['form:nombre'].value;
			var firstname = document.forms['form'].elements['form:apater'].value;
			var secondname = document.forms['form'].elements['form:apater'].value;
			var claveDeUsuario;
			var random = Math.floor(Math.random() * (100 - 0)) + 0;
			
			firstname = firstname.replace(' ','');
			firstname = firstname.replace(' ','');
		    claveDeUsuario = name.charAt(0);
		    claveDeUsuario = claveDeUsuario + firstname; 
		    claveDeUsuario = claveDeUsuario + secondname.charAt(0);
		    claveDeUsuario = claveDeUsuario + String(random);

		    document.forms['form'].elements['form:usua'].value = claveDeUsuario.toUpperCase();
		    document.forms['form'].elements['form:cont'].value = claveDeUsuario.toUpperCase();
		}
