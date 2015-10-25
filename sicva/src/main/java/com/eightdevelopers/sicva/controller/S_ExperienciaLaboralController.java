package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_ExperienciaLaboralDAO;
import com.eightdevelopers.sicva.dto.ExperienciaLaboralDTO;

/**
 * Clase que permite actualizar la tabla 'asistencia_cursos' de la Base de Datos
 * 'SICVA'
 * 
 * @author David Candia
 *
 */

@ManagedBean(name = "S_Experiencia")
@SessionScoped
public class S_ExperienciaLaboralController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;

	private ExperienciaLaboralDTO ExperienciaLaboralDTO;
	private ExperienciaLaboralDTO licSeleccionada;
	private List<ExperienciaLaboralDTO> lista;
	private List<ExperienciaLaboralDTO> filtros;
	private Integer idUsuMod;

	public S_ExperienciaLaboralController() {

		}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void inicializar() {
		S_ExperienciaLaboralDAO s_experiencia = new S_ExperienciaLaboralDAO();
		ExperienciaLaboralDTO = new ExperienciaLaboralDTO();
		lista = s_experiencia.listarExperiencias();
		licSeleccionada = new ExperienciaLaboralDTO();
	}

	public void UpdateExperiencia(ExperienciaLaboralDTO datos) {
		licSeleccionada = datos;
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		idUsuMod = Integer.parseInt(valor);
		S_ExperienciaLaboralDAO s_escolaridad = new S_ExperienciaLaboralDAO();
		String resultado = s_escolaridad.ActualizarExperiencia(licSeleccionada, idUsuMod);
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
		S_ExperienciaLaboralDAO s_escolaridad = new S_ExperienciaLaboralDAO();
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
		S_ExperienciaLaboralDAO s_escolaridad = new S_ExperienciaLaboralDAO();
		String resultado = s_escolaridad.EliminarExperiencia(licSeleccionada);

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

	public ExperienciaLaboralDTO getExperienciaLaboralDTO() {
		return ExperienciaLaboralDTO;
	}

	public void setExperienciaLaboralDTO(ExperienciaLaboralDTO experienciaLaboralDTO) {
		ExperienciaLaboralDTO = experienciaLaboralDTO;
	}

	public ExperienciaLaboralDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(ExperienciaLaboralDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<ExperienciaLaboralDTO> getLista() {
		return lista;
	}

	public void setLista(List<ExperienciaLaboralDTO> lista) {
		this.lista = lista;
	}

	public Integer getIdUsuMod() {
		return idUsuMod;
	}

	public void setIdUsuMod(Integer idUsuMod) {
		this.idUsuMod = idUsuMod;
	}

	public List<ExperienciaLaboralDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<ExperienciaLaboralDTO> filtros) {
		this.filtros = filtros;
	}
}
