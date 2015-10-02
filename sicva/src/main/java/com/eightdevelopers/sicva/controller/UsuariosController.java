package com.eightdevelopers.sicva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.UsuariosDAO;
import com.eightdevelopers.sicva.dto.LicenciaturaDTO;
import com.eightdevelopers.sicva.dto.RolesDTO;
import com.eightdevelopers.sicva.dto.UsuariosDTO;

/**
 * Clase que permite obtener los datos de vista para registrar un nuevo usuario
 * en la tabla 'licenciaturas' de la BD SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */
@ManagedBean(name = "Usuarios")
@SessionScoped

public class UsuariosController implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Variables declaradas para su uso posterior en los métodos siguientes
	 */
	private List<LicenciaturaDTO> lista;
	private List<RolesDTO> listaRol;
	private List<UsuariosDTO> listausu;
	private UsuariosDTO usu;
	private UsuariosDTO usuSeleccionado;

	public UsuariosController() {

		inicializar();

	}

	/**
	 * Método que obtiene los datos para actualizar los registros de la tabla
	 * 'usuarios' de la tabla BD SICVA
	 * 
	 * @param datos
	 */
	public void actualizarDemo(UsuariosDTO dato) {

		usuSeleccionado = dato;
		System.out.println("prueba"+usuSeleccionado.getNombre());
	}

	public void inicializar() {
		UsuariosDAO dao = new UsuariosDAO();
		lista = dao.listarlicenciatura();
		listaRol = dao.listarRoles();
		listausu = dao.listarUsuarios();
		usu = new UsuariosDTO();
		usuSeleccionado = new UsuariosDTO();

	}

	/**
	 * Método que permite instanciar y acceder al método GuardarUsuario de tipo
	 * DAO
	 */
	public void guardar() {
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		String resultado = "";

		resultado = usuariosDAO.GuardarUsuario(usu);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Usuario duplicado"));

		}

		inicializar();
	}

	/**
	 * Método que permite instanciar y acceder al método ActualizarUsu de tipo
	 * DAO
	 */
	public void actualizar() {

		UsuariosDAO usuariosDAO = new UsuariosDAO();
		System.out.println("prueba2"+usuSeleccionado.getNombre());
		String resultado = usuariosDAO.ActualizarUsu(usuSeleccionado);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Usuario duplicado"));

		}

		inicializar();
	}

	/**
	 * Método que permite instanciar y acceder al método EliminarUsu de tipo DAO
	 */

	public void eliminar() {
		UsuariosDAO usuariosDAO = new UsuariosDAO();
		String resultado = usuariosDAO.EliminarUsu(usuSeleccionado);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al eliminar"));

		}

		inicializar();

	}

	public List<LicenciaturaDTO> getLista() {
		return lista;
	}

	public void setLista(List<LicenciaturaDTO> lista) {
		this.lista = lista;
	}

	public UsuariosDTO getUsu() {
		return usu;
	}

	public void setUsu(UsuariosDTO usu) {
		this.usu = usu;
	}

	public List<RolesDTO> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<RolesDTO> listaRol) {
		this.listaRol = listaRol;
	}

	public List<UsuariosDTO> getListausu() {
		return listausu;
	}

	public void setListausu(List<UsuariosDTO> listausu) {
		this.listausu = listausu;
	}

	public UsuariosDTO getUsuSeleccionado() {
		return usuSeleccionado;
	}

	public void setUsuSeleccionado(UsuariosDTO usuSeleccionado) {
		this.usuSeleccionado = usuSeleccionado;
	}

}
