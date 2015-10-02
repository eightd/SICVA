package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.DatosPersonalesDAO;
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Datos_Personales' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Datos")
@SessionScoped
// @ViewScoped
public class DatosPersonalesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private DatosPersonalesDTO datospersonalesDTO;
	private DatosPersonalesDTO licSeleccionada;
	private List<DatosPersonalesDTO> lista;

	public DatosPersonalesController() {

		inicializar();

	}

	public void actualizarDemo(DatosPersonalesDTO datos) {

		licSeleccionada = datos;

	}

	public void inicializar() {
		DatosPersonalesDAO dao = new DatosPersonalesDAO();
		datospersonalesDTO = new DatosPersonalesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new DatosPersonalesDTO();

	}

	public void guardar() {
		DatosPersonalesDAO asistenciacursosDAO = new DatosPersonalesDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(datospersonalesDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		DatosPersonalesDAO asistenciacursosDao = new DatosPersonalesDAO();

		// System.out.println(licSeleccionada.getId());

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

	public void imprimir() {

		System.out.println("Hola mndo");
	}

	public void eliminar() {
		DatosPersonalesDAO asistenciacursosDao = new DatosPersonalesDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

	}

	public DatosPersonalesDTO getDatospersonalesDTO() {
		return datospersonalesDTO;
	}

	public void setDatospersonalesDTO(DatosPersonalesDTO datospersonalesDTO) {
		this.datospersonalesDTO = datospersonalesDTO;
	}

	public DatosPersonalesDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(DatosPersonalesDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<DatosPersonalesDTO> getLista() {
		return lista;
	}

	public void setLista(List<DatosPersonalesDTO> lista) {
		this.lista = lista;
	}

}
