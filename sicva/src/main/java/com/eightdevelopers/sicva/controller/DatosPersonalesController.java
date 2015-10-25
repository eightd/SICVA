package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.DatosPersonalesDAO;
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Datos_Personales' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Datos")
@SessionScoped
public class DatosPersonalesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private DatosPersonalesDTO datospersonalesDTO;
	private DatosPersonalesDTO licSeleccionada;
	private List<DatosPersonalesDTO> lista;
	private Integer id_session;

	public DatosPersonalesController() {

	}
	
	@PostConstruct
	public void init(){
		inicializar();
	}

	public void actualizarDemo(DatosPersonalesDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		DatosPersonalesDAO dao = new DatosPersonalesDAO();
		datospersonalesDTO = new DatosPersonalesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new DatosPersonalesDTO();

	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		datospersonalesDTO.setIdmodif(id_session);
		datospersonalesDTO.setIdusuario(id_session);
		DatosPersonalesDAO asistenciacursosDAO = new DatosPersonalesDAO();
		String resultado = asistenciacursosDAO.guardarDato(datospersonalesDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		inicializar();
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		DatosPersonalesDAO asistenciacursosDao = new DatosPersonalesDAO();
		String resultado = asistenciacursosDao.ActualizarLicenciatura(licSeleccionada, id_session);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));
		}
		inicializar();
	}

	public void ActualizarEvidencia() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		DatosPersonalesDAO datos = new DatosPersonalesDAO();
		String resultado = datos.ActualizarEvidencia(licSeleccionada, id_session);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));
		}
		inicializar();
	}

	public void eliminar() {
		DatosPersonalesDAO asistenciacursosDao = new DatosPersonalesDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al eliminar"));
		}
		inicializar();
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

	public DatosPersonalesDTO getDatospersonalesDTO() {
		return datospersonalesDTO;
	}

	public void setDatospersonalesDTO(DatosPersonalesDTO datospersonalesDTO) {
		this.datospersonalesDTO = datospersonalesDTO;
	}

	public DatosPersonalesDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(DatosPersonalesDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<DatosPersonalesDTO> getLista() {
		return lista;
	}

	public void setLista(List<DatosPersonalesDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}
}
