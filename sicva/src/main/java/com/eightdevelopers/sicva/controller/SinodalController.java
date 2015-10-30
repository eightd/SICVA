package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.SinodalDAO;
import com.eightdevelopers.sicva.dto.SinodalDTO;

@ManagedBean(name = "Sinodal")
@SessionScoped
public class SinodalController implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Sinodal' de la Base de Datos
	 * 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private SinodalDTO sinodalDTO;
	private SinodalDTO licSeleccionada;
	private List<SinodalDTO> lista;
	private Integer id_session;

	public SinodalController() {
	
	}
	
	@PostConstruct
	public void init(){
		inicializar();
	}

	public void actualizarDemo(SinodalDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		SinodalDAO dao = new SinodalDAO();
		sinodalDTO = new SinodalDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new SinodalDTO();

	}

	public void guardar() {
		String valor=(obtenerValorSesion("id"));
		id_session=Integer.parseInt(valor);
		sinodalDTO.setIdmodif(id_session);
		sinodalDTO.setIdusuario(id_session);
		String tipo = sinodalDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){
		SinodalDAO asistenciacursosDAO = new SinodalDAO();
		String resultado = asistenciacursosDAO.guardarDato(sinodalDTO);
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
		SinodalDAO asistenciacursosDao = new SinodalDAO();
		String resultado = asistenciacursosDao.ActualizarLicenciatura(licSeleccionada,id_session );
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
		SinodalDAO sinodal = new SinodalDAO();
		String resultado = sinodal.ActualizarEvidencia(licSeleccionada, id_session);
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
		SinodalDAO asistenciacursosDao = new SinodalDAO();
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

}
