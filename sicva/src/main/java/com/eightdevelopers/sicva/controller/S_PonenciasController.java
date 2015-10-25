package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_PonenciasDAO;
import com.eightdevelopers.sicva.dto.PonenciasDTO;

@ManagedBean(name = "S_Ponencia")
@SessionScoped
public class S_PonenciasController implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Ponencia' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author David Candia
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private PonenciasDTO ponenciasDTO;
	private PonenciasDTO licSeleccionada;
	private List<PonenciasDTO> lista;
	private List<PonenciasDTO> filtros;
	private Integer id_session;

	public S_PonenciasController() {
	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarPonencia(PonenciasDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		S_PonenciasDAO dao = new S_PonenciasDAO();
		ponenciasDTO = new PonenciasDTO();
		lista = dao.listarPonencias();
		licSeleccionada = new PonenciasDTO();
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_PonenciasDAO s_ponencia = new S_PonenciasDAO();
		String resultado = s_ponencia.ActualizarPonencia(licSeleccionada, id_session);
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
		S_PonenciasDAO ponencias = new S_PonenciasDAO();
		String resultado = ponencias.ActualizarEvidencia(licSeleccionada, id_session);
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
		S_PonenciasDAO s_ponencia = new S_PonenciasDAO();
		String resultado = s_ponencia.EliminarPonencia(licSeleccionada);
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

	public PonenciasDTO getPonenciasDTO() {
		return ponenciasDTO;
	}

	public void setPonenciasDTO(PonenciasDTO ponenciasDTO) {
		this.ponenciasDTO = ponenciasDTO;
	}

	public PonenciasDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(PonenciasDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<PonenciasDTO> getLista() {
		return lista;
	}

	public void setLista(List<PonenciasDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public List<PonenciasDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<PonenciasDTO> filtros) {
		this.filtros = filtros;
	}
}