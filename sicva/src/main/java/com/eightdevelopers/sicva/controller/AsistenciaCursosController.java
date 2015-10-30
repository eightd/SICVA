package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.AsistenciaCursosDAO;
import com.eightdevelopers.sicva.dto.AsistenciaCursosDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Asistencia_Cursos' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Asistencia")
@SessionScoped
public class AsistenciaCursosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private AsistenciaCursosDTO asistenciacursosDTO;
	private AsistenciaCursosDTO licSeleccionada;
	private List<AsistenciaCursosDTO> lista;
	private Integer id_session;

	public AsistenciaCursosController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(AsistenciaCursosDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		AsistenciaCursosDAO dao = new AsistenciaCursosDAO();
		asistenciacursosDTO = new AsistenciaCursosDTO();
		setLista(dao.listarasistenciacursos());
		licSeleccionada = new AsistenciaCursosDTO();

	}

	public void guardar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		asistenciacursosDTO.setIdmodif(id_session);
		asistenciacursosDTO.setIdusuario(id_session);
		String tipo = asistenciacursosDTO.getEvidencia().getContentType();
		String formato[]=tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if(formato[1].equals("jpeg")||formato[1].equals("png")||formato[1].equals("jpg")||formato[1].equals("octet-stream")){
		AsistenciaCursosDAO asistenciacursosDAO = new AsistenciaCursosDAO();
		String resultado = asistenciacursosDAO.guardarDato(asistenciacursosDTO);
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
		AsistenciaCursosDAO asistenciacursosDao = new AsistenciaCursosDAO();
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
				AsistenciaCursosDAO asistenciacursosDao = new AsistenciaCursosDAO();
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
		AsistenciaCursosDAO asistenciacursosDao = new AsistenciaCursosDAO();
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

	public AsistenciaCursosDTO getAsistenciacursosDTO() {
		return asistenciacursosDTO;
	}

	public void setAsistenciacursosDTO(AsistenciaCursosDTO asistenciacursosDTO) {
		this.asistenciacursosDTO = asistenciacursosDTO;
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

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

}
