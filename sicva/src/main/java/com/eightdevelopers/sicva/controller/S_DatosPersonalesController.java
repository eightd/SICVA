package com.eightdevelopers.sicva.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.S_DatosPersonalesDAO;
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

/**
 * Clase que permite actualizar la tabla 'datos_personales' de la Base de Datos
 * 'SICVA'
 * 
 * @author David Candia
 *
 */

@ManagedBean(name = "S_DatosPersonales")
@SessionScoped
public class S_DatosPersonalesController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;

	private DatosPersonalesDTO datospersonalesDTO;
	private DatosPersonalesDTO licSeleccionada;
	private List<DatosPersonalesDTO> lista;
	private List<DatosPersonalesDTO> filtros;
	private Integer id_session;

	public S_DatosPersonalesController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void inicializar() {
		S_DatosPersonalesDAO s_datos = new S_DatosPersonalesDAO();
		datospersonalesDTO = new DatosPersonalesDTO();
		lista = s_datos.ListarDatosPersonales();
		licSeleccionada = new DatosPersonalesDTO();
	}

	public void UpdateDatos(DatosPersonalesDTO datos) {
		licSeleccionada = datos;
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_DatosPersonalesDAO s_datos = new S_DatosPersonalesDAO();
		String resultado = s_datos.ActualizarDatos(licSeleccionada,id_session);
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
		S_DatosPersonalesDAO s_datos = new S_DatosPersonalesDAO();
		String resultado = s_datos.ActualizarEvidencia(licSeleccionada,id_session);
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
		S_DatosPersonalesDAO s_datos = new S_DatosPersonalesDAO();
		String resultado = s_datos.EliminarDatos(licSeleccionada);
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

	public List<DatosPersonalesDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<DatosPersonalesDTO> filtros) {
		this.filtros = filtros;
	}
}
