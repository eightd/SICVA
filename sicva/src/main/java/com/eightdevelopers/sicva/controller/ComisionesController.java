package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;


import com.eightdevelopers.sicva.dao.ComisionesDAO;
import com.eightdevelopers.sicva.dto.ComisionesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Comisiones' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Comisiones")
@SessionScoped
public class ComisionesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private ComisionesDTO comisionesDTO;
	private ComisionesDTO licSeleccionada;
	private List<ComisionesDTO> lista;
	private Integer id_session;

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public ComisionesController() {
		
	}
	
	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(ComisionesDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		ComisionesDAO dao = new ComisionesDAO();
		comisionesDTO = new ComisionesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new ComisionesDTO();

	}

	public void guardar() {
		String valor=(obtenerValorSesion("id"));
		id_session=Integer.parseInt(valor);
		comisionesDTO.setIdmodif(id_session);
		comisionesDTO.setIdusuario(id_session);
		String tipo = comisionesDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){
		ComisionesDAO asistenciacursosDAO = new ComisionesDAO();
		String resultado = asistenciacursosDAO.guardarDato(comisionesDTO);
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
		ComisionesDAO asistenciacursosDao = new ComisionesDAO();
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
		ComisionesDAO asistenciacursosDao = new ComisionesDAO();
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
		ComisionesDAO asistenciacursosDao = new ComisionesDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
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

	

	public ComisionesDTO getComisionesDTO() {
		return comisionesDTO;
	}

	public void setComisionesDTO(ComisionesDTO comisionesDTO) {
		this.comisionesDTO = comisionesDTO;
	}

	public ComisionesDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(ComisionesDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<ComisionesDTO> getLista() {
		return lista;
	}

	public void setLista(List<ComisionesDTO> lista) {
		this.lista = lista;
	}

}
