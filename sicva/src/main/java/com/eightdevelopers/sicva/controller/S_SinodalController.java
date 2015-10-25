package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_SinodalDAO;
import com.eightdevelopers.sicva.dto.SinodalDTO;

/**
 * 
 * @author David Candia
 *
 */
@ManagedBean(name = "S_Sinodal")
@SessionScoped
public class S_SinodalController implements Serializable {
	private static final long serialVersionUID = 1L;

	private SinodalDTO sinodalDTO;
	private SinodalDTO licSeleccionada;
	private List<SinodalDTO> lista;
	private List<SinodalDTO> filtros;
	private Integer id_session;

	public S_SinodalController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(SinodalDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		S_SinodalDAO dao = new S_SinodalDAO();
		sinodalDTO = new SinodalDTO();
		lista = dao.ListarSinodales();
		licSeleccionada = new SinodalDTO();

	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_SinodalDAO s_sinodal = new S_SinodalDAO();
		String resultado = s_sinodal.ActualizarSinodal(licSeleccionada, id_session);
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
		S_SinodalDAO sinodal = new S_SinodalDAO();
		String resultado = sinodal.ActualizarEvidencia(licSeleccionada, id_session);
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
		S_SinodalDAO s_sinodal = new S_SinodalDAO();
		String resultado = s_sinodal.EliminarSinodal(licSeleccionada);
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

	public SinodalDTO getSinodalDTO() {
		return sinodalDTO;
	}

	public void setSinodalDTO(SinodalDTO sinodalDTO) {
		this.sinodalDTO = sinodalDTO;
	}

	public SinodalDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(SinodalDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<SinodalDTO> getLista() {
		return lista;
	}

	public void setLista(List<SinodalDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public List<SinodalDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<SinodalDTO> filtros) {
		this.filtros = filtros;
	}

}