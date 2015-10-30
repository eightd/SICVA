package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.PonenciasDAO;
import com.eightdevelopers.sicva.dto.PonenciasDTO;

@ManagedBean(name = "Ponencia")
@SessionScoped
public class PonenciasController implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Ponencia' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private PonenciasDTO ponenciasDTO;
	private PonenciasDTO licSeleccionada;
	private List<PonenciasDTO> lista;
	private Integer id_session;

	public PonenciasController() {
	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(PonenciasDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		PonenciasDAO dao = new PonenciasDAO();
		ponenciasDTO = new PonenciasDTO();
		lista = dao.listarponencias();
		licSeleccionada = new PonenciasDTO();
	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		ponenciasDTO.setIdmodif(id_session);
		ponenciasDTO.setIdusuario(id_session);	
		String tipo = ponenciasDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){	
		PonenciasDAO asistenciacursosDAO = new PonenciasDAO();
		String resultado = asistenciacursosDAO.guardarDato(ponenciasDTO);
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
		PonenciasDAO ponenciasDao = new PonenciasDAO();
		String resultado = ponenciasDao.ActualizarLicenciatura(licSeleccionada, id_session);
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
	
		PonenciasDAO ponencias = new PonenciasDAO();
		String resultado = ponencias.ActualizarEvidencia(licSeleccionada, id_session);
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
		PonenciasDAO asistenciacursosDao = new PonenciasDAO();
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
}
