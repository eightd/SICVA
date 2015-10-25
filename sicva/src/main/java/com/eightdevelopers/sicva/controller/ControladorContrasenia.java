package com.eightdevelopers.sicva.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.ContraseniaDAO;
import com.eightdevelopers.sicva.dto.ContraseniaDTO;

@ManagedBean(name = "controlador")
@RequestScoped
@SessionScoped
public class ControladorContrasenia {
	private ContraseniaDTO password;

	public ControladorContrasenia() {
		inicializar();

	}

	public void inicializar() {
		password = new ContraseniaDTO();
	}

	public void verificaUsuario() {

		System.out.println("valor::: ID ::  " + bajarId("id"));

		FacesContext currentInstance = FacesContext.getCurrentInstance();
		try {
			if (password.getPass() == "" || password.getPassNuevo() == "" || password.getPassNuevo2() == "") {
				FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_WARN, "Contraseña", " Campos requeridos ");
				currentInstance.addMessage(null, fm);
			} else {

				if (password.getPassNuevo().equals(password.getPassNuevo2())) {
					ContraseniaDAO c = new ContraseniaDAO();
					boolean valido = c.verifica(password, bajarId("id"));

					if (valido) { 
						System.out.println("existencia de usuario");

						boolean actualizado = c.update(password, bajarId("id"));
						if (actualizado) {
							System.out.println("Contraseña acualizado correctamente !!! ");
							FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña",
									" Actualizado correctamente ");
							currentInstance.addMessage(null, fm);

							cerrarSesion();
						} else {
							System.out.println("NO!!! actualiza");
							FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, " ",
									" No se pudo actualizar tu contraseña ");
							currentInstance.addMessage(null, fm);

						}
					} else {
						System.out.println("no hay usuario");
						FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								" Contraseña anterior no válido ");
						currentInstance.addMessage(null, fm);
					}
					inicializar();
				} else {
					FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							" Las contraseñas no coinciden ");
					currentInstance.addMessage(null, fm);
				}
			}

		} catch (Exception e) {

		}
	}

	/**
	 * Metodo para obtener un valor
	 * 
	 * @param clave
	 * @return
	 */
	public String bajarId(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	public void cerrarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();
			redireccionar("index.xhtml");
		} catch (Exception ex) {

		}
	}

	public void redireccionar(String ruta) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(ruta);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ContraseniaDTO getPassword() {
		return password;
	}

	public void setPassword(ContraseniaDTO password) {
		this.password = password;
	}
}
