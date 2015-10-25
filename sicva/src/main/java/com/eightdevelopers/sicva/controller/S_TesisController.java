package com.eightdevelopers.sicva.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.S_TesisDAO;
import com.eightdevelopers.sicva.dto.TesisDTO;

/**
 * 
 * @author David Candia
 *
 */
@ManagedBean(name = "S_Tesis")
@SessionScoped
public class S_TesisController implements Serializable {

	private static final long serialVersionUID = 1L;
	private TesisDTO tesisDTO;
	private TesisDTO licSeleccionada;
	private List<TesisDTO> lista;
	private List<TesisDTO> filtros;
	private Integer id_session;

	public S_TesisController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(TesisDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		S_TesisDAO dao = new S_TesisDAO();
		tesisDTO = new TesisDTO();
		lista = dao.listarAsosoriasTesis();
		licSeleccionada = new TesisDTO();
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_TesisDAO s_tesis = new S_TesisDAO();
		String resultado = s_tesis.ActualizarAsesoriasTesis(licSeleccionada, id_session);
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
		S_TesisDAO tesis = new S_TesisDAO();
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
		S_TesisDAO asistenciacursosDao = new S_TesisDAO();
		String resultado = asistenciacursosDao.EliminarAsesoriaTesis(licSeleccionada);
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

	public List<TesisDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<TesisDTO> filtros) {
		this.filtros = filtros;
	}
}
