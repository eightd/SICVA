package com.eightdevelopers.sicva.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.LicenciaturaDAO;
import com.eightdevelopers.sicva.dto.LicenciaturaDTO;

/**
 * Clase que permite obtener los datos de vista para registrar una nueva
 * licenciatura en la tabla 'licenciaturas' de la BD SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */

@ManagedBean(name = "Licenciaturas")
@SessionScoped

public class LicenciaturaController implements Serializable {

	private static final long serialVersionUID = -2715913227853027610L;
	/**
	 * Variables declaradas para su uso posterior en los métodos siguientes
	 */
	private LicenciaturaDTO licenciaturaDTO;
	private LicenciaturaDTO licSeleccionada;
	private List<LicenciaturaDTO> lista;
	private List<LicenciaturaDTO> filtros;

	public LicenciaturaController() {

		inicializar();

	}

	/**
	 * Método que obtiene los datos para actualizar los registros de la tabla
	 * 'licenciaturas' BD SICVA
	 * 
	 * @param datos
	 */
	public void actualizarDemo(LicenciaturaDTO datos) {
		licSeleccionada = datos;

	}

	public void inicializar() {
		LicenciaturaDAO dao = new LicenciaturaDAO();
		licenciaturaDTO = new LicenciaturaDTO();
		lista = dao.listarlicenciatura();
		licSeleccionada = new LicenciaturaDTO();

	}

	/**
	 * Método que permite instanciar y acceder al método guardarDato de tipo DAO
	 */
	public void guardar() {
		LicenciaturaDAO licenciaturaDAO = new LicenciaturaDAO();
		String resultado = "";

		resultado = licenciaturaDAO.guardarDato(licenciaturaDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Licenciatura duplicada"));

		}

		inicializar();
	}

	/**
	 * Método que permite instaciar y acceder al método ActualizarLicenciatura
	 * de tipo DAO
	 */
	public void actualizar() {

		LicenciaturaDAO licenciaturaDao = new LicenciaturaDAO();

		String resultado = licenciaturaDao.ActualizarLicenciatura(licSeleccionada);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Licenciatura duplicada"));

		}

		inicializar();
	}

	/**
	 * Método que permite instaciar y acceder al método EliminarLicen de tipo
	 * DAO
	 */
	public void eliminar() {
		LicenciaturaDAO licenciaturaDao = new LicenciaturaDAO();
		String resultado = licenciaturaDao.EliminarLicen(licSeleccionada);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al eliminar"));

		}

		inicializar();

	}

	public LicenciaturaDTO getLicenciaturaDTO() {
		return licenciaturaDTO;
	}

	public void setLicenciaturaDTO(LicenciaturaDTO licenciaturaDTO) {
		this.licenciaturaDTO = licenciaturaDTO;
	}

	public List<LicenciaturaDTO> getLista() {
		return lista;
	}

	public void setLista(List<LicenciaturaDTO> lista) {
		this.lista = lista;
	}

	public LicenciaturaDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(LicenciaturaDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<LicenciaturaDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<LicenciaturaDTO> filtros) {
		this.filtros = filtros;
	}

}
