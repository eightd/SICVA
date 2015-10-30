package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;


import com.eightdevelopers.sicva.dao.PublicacionesDAO;
import com.eightdevelopers.sicva.dto.PublicacionesDTO;


@ManagedBean(name = "Publicaciones")
@SessionScoped
public class PublicacionesController implements Serializable {
	/**
	 * Clase que permite hacer el CRUD de la tabla 'Publicaciones' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private PublicacionesDTO publicacionesDTO;
	private PublicacionesDTO licSeleccionada;
	private List<PublicacionesDTO> lista;
	private Integer id_session;

	
	public PublicacionesController() {
	}
	
	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(PublicacionesDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		PublicacionesDAO dao = new PublicacionesDAO();
		publicacionesDTO = new PublicacionesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new PublicacionesDTO();

	}

	public void guardar() {
		String valor=(obtenerValorSesion("id"));
		id_session=Integer.parseInt(valor);
		publicacionesDTO.setIdmodif(id_session);
		publicacionesDTO.setIdusuario(id_session);
		String tipo = publicacionesDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){

		PublicacionesDAO asistenciacursosDAO = new PublicacionesDAO();
		String resultado = asistenciacursosDAO.guardarDato(publicacionesDTO);

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
		PublicacionesDAO asistenciacursosDao = new PublicacionesDAO();
		String resultado = asistenciacursosDao.ActualizarLicenciatura(licSeleccionada,id_session);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
	}

	public void ActualizarEvidencia(){
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		String tipo =  licSeleccionada.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")){
		PublicacionesDAO asistenciacursosDao = new PublicacionesDAO();
		String resultado = asistenciacursosDao.ActualizarEvidencia(licSeleccionada, id_session);   
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
		PublicacionesDAO asistenciacursosDao = new PublicacionesDAO();
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

}
