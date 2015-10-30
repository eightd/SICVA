package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.MiembroDAO;
import com.eightdevelopers.sicva.dto.MiembroDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Miembro' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Miembro")
@SessionScoped
public class MiembroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private MiembroDTO miembroDTO;
	private MiembroDTO licSeleccionada;
	private List<MiembroDTO> lista;
	private Integer id_session;

	public MiembroController() {

	}
	
	@PostConstruct
	public void init(){
		inicializar();
	}

	public void actualizarDemo(MiembroDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		MiembroDAO dao = new MiembroDAO();
		miembroDTO = new MiembroDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new MiembroDTO();
	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		miembroDTO.setIdmodif(id_session);
		miembroDTO.setIdusuario(id_session);
		String tipo = miembroDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){

		MiembroDAO asistenciacursosDAO = new MiembroDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(miembroDTO);
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
		MiembroDAO asistenciacursosDao = new MiembroDAO();
		String resultado = asistenciacursosDao.ActualizarLicenciatura(licSeleccionada);
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
		MiembroDAO escolaridad = new MiembroDAO();
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
		MiembroDAO asistenciacursosDao = new MiembroDAO();
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

	public MiembroDTO getMiembroDTO() {
		return miembroDTO;
	}

	public void setMiembroDTO(MiembroDTO miembroDTO) {
		this.miembroDTO = miembroDTO;
	}

	public MiembroDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(MiembroDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<MiembroDTO> getLista() {
		return lista;
	}

	public void setLista(List<MiembroDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

}
