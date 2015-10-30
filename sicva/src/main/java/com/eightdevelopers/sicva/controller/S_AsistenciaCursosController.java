package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_AsistenciaCursosDAO;
import com.eightdevelopers.sicva.dto.AsistenciaCursosDTO;

/**
 * Clase que permite actualizar la tabla 'Escolaridad' de la Base de
 * Datos 'SICVA'
 * 
 * @author David Candia
 *
 */

@ManagedBean(name = "S_Asistencia")
@SessionScoped
public class S_AsistenciaCursosController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;

	private AsistenciaCursosDTO asistenciaCursosDTO;
	private AsistenciaCursosDTO licSeleccionada;
	private List<AsistenciaCursosDTO> lista;
	private List<AsistenciaCursosDTO> filtros;
	private Integer idUsuMod;

	public S_AsistenciaCursosController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void inicializar() {
		S_AsistenciaCursosDAO s_asistencia = new S_AsistenciaCursosDAO();
		asistenciaCursosDTO = new AsistenciaCursosDTO();
		lista = s_asistencia.listarasistenciacursos();
		licSeleccionada = new AsistenciaCursosDTO();
	}

	public void UpdateAsistencia(AsistenciaCursosDTO datos) {
		licSeleccionada = datos;
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		idUsuMod = Integer.parseInt(valor);
		S_AsistenciaCursosDAO asistencia= new S_AsistenciaCursosDAO();
		String resultado = asistencia.ActualizarAsistencia(licSeleccionada, idUsuMod);
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
		String tipo =  licSeleccionada.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")){
		S_AsistenciaCursosDAO asistencia= new S_AsistenciaCursosDAO();
		String resultado = asistencia.ActualizarEvidencia(licSeleccionada, idUsuMod);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));
		}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Formato de imagen no válido"));
		}
		inicializar();
	}

	public void eliminar() {
		S_AsistenciaCursosDAO asistencia= new S_AsistenciaCursosDAO();
		String resultado = asistencia.EliminarAsistenciaCurso(licSeleccionada);
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

	public AsistenciaCursosDTO getAsistenciaCursosDTO() {
		return asistenciaCursosDTO;
	}

	public void setAsistenciaCursosDTO(AsistenciaCursosDTO asistenciaCursosDTO) {
		this.asistenciaCursosDTO = asistenciaCursosDTO;
	}

	public AsistenciaCursosDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(AsistenciaCursosDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<AsistenciaCursosDTO> getLista() {
		return lista;
	}

	public void setLista(List<AsistenciaCursosDTO> lista) {
		this.lista = lista;
	}

	public Integer getIdUsuMod() {
		return idUsuMod;
	}

	public void setIdUsuMod(Integer idUsuMod) {
		this.idUsuMod = idUsuMod;
	}

	public List<AsistenciaCursosDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<AsistenciaCursosDTO> filtros) {
		this.filtros = filtros;
	}

}
