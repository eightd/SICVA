package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_EscolaridadDAO;
import com.eightdevelopers.sicva.dto.EscolaridadDTO;

/**
 * Clase que permite actualizar la tabla 'asistencia_cursos' de la Base de
 * Datos 'SICVA'
 * 
 * @author David Candia
 *
 */

@ManagedBean(name = "S_Escolaridad")
@SessionScoped
public class S_EscolaridadController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;

	private EscolaridadDTO escolaridaDTO;
	private EscolaridadDTO licSeleccionada;
	private List<EscolaridadDTO> lista;
	private List<EscolaridadDTO> filtros;
	private Integer idUsuMod;

	public S_EscolaridadController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void inicializar() {
		S_EscolaridadDAO s_escolaridad = new S_EscolaridadDAO();
		escolaridaDTO = new EscolaridadDTO();
		lista = s_escolaridad.ListarEscolaridades();
		licSeleccionada = new EscolaridadDTO();
	}

	public void UpdateEscolaridad(EscolaridadDTO datos) {
		licSeleccionada = datos;
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		idUsuMod = Integer.parseInt(valor);
		S_EscolaridadDAO s_escolaridad = new S_EscolaridadDAO();
		String resultado = s_escolaridad.ActualizarEscolaridad(licSeleccionada, idUsuMod);
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
		idUsuMod = Integer.parseInt(valor);
		S_EscolaridadDAO s_escolaridad = new S_EscolaridadDAO();
		String resultado = s_escolaridad.ActualizarEvidencia(licSeleccionada, idUsuMod);
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
		S_EscolaridadDAO s_escolaridad = new S_EscolaridadDAO();
		String resultado = s_escolaridad.EliminarEscolaridad(licSeleccionada);

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

	public EscolaridadDTO getEscolaridaDTO() {
		return escolaridaDTO;
	}

	public void setEscolaridaDTO(EscolaridadDTO escolaridaDTO) {
		this.escolaridaDTO = escolaridaDTO;
	}

	public EscolaridadDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(EscolaridadDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<EscolaridadDTO> getLista() {
		return lista;
	}

	public void setLista(List<EscolaridadDTO> lista) {
		this.lista = lista;
	}

	public Integer getIdUsuMod() {
		return idUsuMod;
	}

	public void setIdUsuMod(Integer idUsuMod) {
		this.idUsuMod = idUsuMod;
	}

	public List<EscolaridadDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<EscolaridadDTO> filtros) {
		this.filtros = filtros;
	}

}
