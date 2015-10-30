package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.ExperienciaLaboralDAO;
import com.eightdevelopers.sicva.dto.ExperienciaLaboralDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Experiencia_Laboral' de la Base
 * de Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Experiencia")
@SessionScoped
public class ExperienciaLaboralController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private ExperienciaLaboralDTO experienciaDTO;
	private ExperienciaLaboralDTO licSeleccionada;
	private List<ExperienciaLaboralDTO> lista;
	private Integer id_session;

	public ExperienciaLaboralController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(ExperienciaLaboralDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		ExperienciaLaboralDAO dao = new ExperienciaLaboralDAO();
		experienciaDTO = new ExperienciaLaboralDTO();
		lista = dao.listarexperiencia();
		licSeleccionada = new ExperienciaLaboralDTO();

	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		experienciaDTO.setIdmodif(id_session);
		experienciaDTO.setIdusuario(id_session);
		String tipo = experienciaDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){
		ExperienciaLaboralDAO asistenciacursosDAO = new ExperienciaLaboralDAO();
		String resultado = asistenciacursosDAO.guardarDato(experienciaDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada", "Fallo al guardar"));
		}
		}else{
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Formato de imagen no válido"));
		}
		
		inicializar();
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		ExperienciaLaboralDAO ponenciasDao = new ExperienciaLaboralDAO();
		String resultado = ponenciasDao.ActualizarLicenciatura(licSeleccionada, id_session);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
	}

	public void ActualizarEvidencia() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		String tipo =  licSeleccionada.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")){
		ExperienciaLaboralDAO experiencia = new ExperienciaLaboralDAO();
		String resultado = experiencia.ActualizarEvidencia(licSeleccionada, id_session);
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
		ExperienciaLaboralDAO asistenciacursosDao = new ExperienciaLaboralDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada", "Fallo al eliminar"));
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

	public ExperienciaLaboralDTO getExperienciaDTO() {
		return experienciaDTO;
	}

	public void setExperienciaDTO(ExperienciaLaboralDTO experienciaDTO) {
		this.experienciaDTO = experienciaDTO;
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

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

}
