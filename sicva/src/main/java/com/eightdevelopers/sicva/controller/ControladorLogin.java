package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.LoginDAO;
import com.eightdevelopers.sicva.dto.UsuarioDTO;

@ManagedBean(name = "ControladorLogin")
@SessionScoped
/**
 * Hace las acciones necesarias para el inicio de sesión
 * 
 * @author Leoonardo Zavala
 * @version 1.0
 * 
 */
public class ControladorLogin implements Serializable {

	private static final long serialVersionUID = 1L;
	private String pwd;
	private String msg;
	private String user;
	private Integer Rol = 1;
	private Integer id;
	private String pwd1;
	private String user1;
	private Integer contra_esta;

	/**
	 * Verifica que el usuario en sesión sea admnistrador
	 * 
	 * @param rol
	 * @return true o false
	 */

	public boolean tieneRol1(String rol) {

		String valor = obtenerValorSesion(rol);
		if (valor == null)
			return false;
		if (valor.equals("1")) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Verifica que el usuario en sesión sea coordinador
	 * 
	 * @param rol
	 * @return true o false
	 */
	public boolean tieneRol2(String rol) {

		String valor = obtenerValorSesion(rol);
		if (valor == null)
			return false;
		if (valor.equals("2")) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Verifica que el usuario en sesión sea secretaria
	 * 
	 * @param rol
	 * @return true o false;
	 */
	public boolean tieneRol3(String rol) {

		String valor = obtenerValorSesion(rol);
		if (valor == null)
			return false;
		if (valor.equals("3")) {

			return true;

		} else {
			return false;
		}

	}

	/**
	 * Verifica que el usuario en sesión sea docente
	 * 
	 * @param rol
	 * @return true o false
	 */
	public boolean tieneRol4(String rol) {

		String valor = obtenerValorSesion(rol);
		if (valor == null)
			return false;
		if (valor.equals("4")) {

			return true;

		} else {
			return false;
		}

	}

	public void cerrarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();// Se borrar
																// valores en
																// sesion
			redireccionar("index.xhtml");
		} catch (Exception ex) {

		}
	}

	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	public void subirValorSesion(String clave, String valor) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put(clave, valor);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void mostrarMensaje(String mensaje) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(mensaje));
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

	/**
	 * verifica si el usuario esta registrado en la base de datos
	 */
	public void validate() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();
		} catch (Exception ex) {

		}
		LoginDAO login = new LoginDAO();
		List<UsuarioDTO> res;
		res = login.isAcountExists(user, pwd);

		if (res != null) {
			Rol = res.get(0).getRol();
			contra_esta = res.get(0).getContra_estado();
			if (contra_esta == 0) {
				subirValorSesion("nombre", user);
				cargarIdUsuario(user, pwd);
				cargarIdRol(user, pwd);
				cargarIdLic(user, pwd);
				redireccionar("CambioContrasenia.xhtml");
				return;
			}
			if (contra_esta == 1) {
				subirValorSesion("nombre", user);
				cargarIdUsuario(user, pwd);
				cargarIdRol(user, pwd);
				cargarIdLic(user, pwd);
				redireccionar("Principal.xhtml");
				return;
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Datos Incorrectos ó credenciales incorrectas", "Verifica tus datos"));

		}
	}

	public void cargarIdUsuario(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);
		subirValorSesion("id", listado.get(0).getId() + "");
	}

	public void cargarIdRol(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);
		subirValorSesion("rol", listado.get(0).getRol() + "");
	}

	public void cargarIdLic(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);
		subirValorSesion("lic", listado.get(0).getId_lic() + "");

	}

	public void cargarContraes(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);
		subirValorSesion("ces", listado.get(0).getContra_estado() + "");
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRol() {
		return Rol;
	}

	public void setRol(Integer rol) {
		Rol = rol;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getContra_esta() {
		return contra_esta;
	}

	public void setContra_esta(Integer contra_esta) {
		this.contra_esta = contra_esta;
	}

}
