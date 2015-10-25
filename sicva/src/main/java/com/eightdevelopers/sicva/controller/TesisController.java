package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.TesisDAO;
import com.eightdevelopers.sicva.dto.TesisDTO;
/**
 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */
@ManagedBean(name = "Tesis")
@SessionScoped
public class TesisController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private TesisDTO tesisDTO;
	private TesisDTO licSeleccionada;
	private List<TesisDTO> lista;
	private Integer id_session;

	public TesisController() {
	
	}
	
	@PostConstruct
	public void init(){
		inicializar();	
	}
	
	public void actualizarDemo(TesisDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		TesisDAO dao = new TesisDAO();
		tesisDTO = new TesisDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new TesisDTO();

	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		tesisDTO.setIdmodif(id_session);
		tesisDTO.setIdusuario(id_session);
		TesisDAO asistenciacursosDAO = new TesisDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(tesisDTO);
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
		TesisDAO asistenciacursosDao = new TesisDAO();
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
		TesisDAO tesis = new TesisDAO();
		String resultado = tesis.ActualizarEvidencia(licSeleccionada, id_session);
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
		TesisDAO asistenciacursosDao = new TesisDAO();
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

	public TesisDTO getTesisDTO() {
		return tesisDTO;
	}

	public void setTesisDTO(TesisDTO tesisDTO) {
		this.tesisDTO = tesisDTO;
	}

	public TesisDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(TesisDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<TesisDTO> getLista() {
		return lista;
	}

	public void setLista(List<TesisDTO> lista) {
		this.lista = lista;

	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

}
