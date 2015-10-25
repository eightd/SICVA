package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

import com.eightdevelopers.sicva.dao.S_PublicacionesDAO;
import com.eightdevelopers.sicva.dto.PublicacionesDTO;

@ManagedBean(name = "S_Publicaciones")
@SessionScoped
/**
 * 
 * @author David Candia
 *
 */
public class S_PublicacionesController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;

	private PublicacionesDTO publicacionesDTO;
	private PublicacionesDTO licSeleccionada;
	private List<PublicacionesDTO> lista;
	private List<PublicacionesDTO> filtros;
	private Integer id_session;

	public S_PublicacionesController() {
	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void inicializar() {
		S_PublicacionesDAO dao = new S_PublicacionesDAO();
		publicacionesDTO = new PublicacionesDTO();
		lista = dao.listarPublicaciones();
		licSeleccionada = new PublicacionesDTO();

	}

	public void actualizarDemo(PublicacionesDTO datos) {
		licSeleccionada = datos;
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_PublicacionesDAO s_publicaciones = new S_PublicacionesDAO();
		String resultado = s_publicaciones.ActualizarPublicacion(licSeleccionada, id_session);
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
		S_PublicacionesDAO s_publicaciones = new S_PublicacionesDAO();
		String resultado = s_publicaciones.ActualizarEvidencia(licSeleccionada, id_session);
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
		S_PublicacionesDAO asistenciacursosDao = new S_PublicacionesDAO();
		String resultado = asistenciacursosDao.EliminarPublicacion(licSeleccionada);
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

	public PublicacionesDTO getPublicacionesDTO() {
		return publicacionesDTO;
	}

	public void setPublicacionesDTO(PublicacionesDTO publicacionesDTO) {
		this.publicacionesDTO = publicacionesDTO;
	}

	public PublicacionesDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(PublicacionesDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<PublicacionesDTO> getLista() {
		return lista;
	}

	public void setLista(List<PublicacionesDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public List<PublicacionesDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<PublicacionesDTO> filtros) {
		this.filtros = filtros;
	}
}