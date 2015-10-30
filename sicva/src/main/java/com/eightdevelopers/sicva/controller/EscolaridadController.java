package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.EscolaridadDAO;
import com.eightdevelopers.sicva.dto.EscolaridadDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Escolaridad' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Escolaridad")
@SessionScoped
public class EscolaridadController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private EscolaridadDTO escolaridaDTO;
	private EscolaridadDTO licSeleccionada;
	private List<EscolaridadDTO> lista;
	private Integer id_session;

	public EscolaridadController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(EscolaridadDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		EscolaridadDAO dao = new EscolaridadDAO();
		escolaridaDTO = new EscolaridadDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new EscolaridadDTO();
	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		escolaridaDTO.setIdmodif(id_session);
		escolaridaDTO.setIdusuario(id_session);	
		String tipo = escolaridaDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){
		EscolaridadDAO asistenciacursosDAO = new EscolaridadDAO();
		String resultado = asistenciacursosDAO.guardarDato(escolaridaDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
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
		EscolaridadDAO asistenciacursosDao = new EscolaridadDAO();
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
		String tipo =  licSeleccionada.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")){
		EscolaridadDAO escolaridad = new EscolaridadDAO();
		String resultado = escolaridad.ActualizarEvidencia(licSeleccionada, id_session);
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
		EscolaridadDAO asistenciacursosDao = new EscolaridadDAO();
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

	public EscolaridadDTO getescolaridaDTO() {
		return escolaridaDTO;
	}

	public void setAsistenciacursosDTO(EscolaridadDTO asistenciacursosDTO) {
		this.escolaridaDTO = asistenciacursosDTO;
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

	public EscolaridadDTO getEscolaridaDTO() {
		return escolaridaDTO;
	}

	public void setEscolaridaDTO(EscolaridadDTO escolaridaDTO) {
		this.escolaridaDTO = escolaridaDTO;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

}
