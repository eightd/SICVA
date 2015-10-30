package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.List;

import com.eightdevelopers.sicva.dao.S_MiembroDAO;
import com.eightdevelopers.sicva.dto.MiembroDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Miembro' de la Base de Datos
 * 'SICVA'
 * 
 * @author David Candia
 *
 */

@ManagedBean(name = "S_Miembro")
@SessionScoped
public class S_MiembroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MiembroDTO miembroDTO;
	private MiembroDTO licSeleccionada;
	private List<MiembroDTO> lista;
	private List<MiembroDTO> filtros;
	private Integer id_session;

	public S_MiembroController() {

	}

	@PostConstruct
	public void init() {
		inicializar();
	}

	public void actualizarDemo(MiembroDTO datos) {
		licSeleccionada = datos;
	}

	public void inicializar() {
		S_MiembroDAO dao = new S_MiembroDAO();
		miembroDTO = new MiembroDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new MiembroDTO();
	}

	public void actualizar() {
		String valor = (obtenerValorSesion("id"));
		id_session = Integer.parseInt(valor);
		S_MiembroDAO s_miembro = new S_MiembroDAO();
		String resultado = s_miembro.ActualizarLicenciatura(licSeleccionada, id_session);
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
		String tipo = licSeleccionada.getEvidencia().getContentType();
		String formato[] = tipo.split("/");
		System.out.println(" ........... " + formato[1]);
		if (formato[1].equals("jpeg") || formato[1].equals("png") || formato[1].equals("jpg")) {
			S_MiembroDAO s_miembro = new S_MiembroDAO();
			String resultado = s_miembro.ActualizarMiembro(licSeleccionada, id_session);
			if (resultado != "") {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Formato de imagen no válido"));
		}
		inicializar();
	}

	public void eliminar() {
		S_MiembroDAO s_miembro = new S_MiembroDAO();
		String resultado = s_miembro.EliminarMiembro(licSeleccionada);
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

	public List<MiembroDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<MiembroDTO> filtros) {
		this.filtros = filtros;
	}

}
