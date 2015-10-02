package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.TesisDAO;
import com.eightdevelopers.sicva.dto.TesisDTO;

@ManagedBean(name = "Tesis")
@SessionScoped
// @ViewScoped
public class TesisController implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de Datos
	 * 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private TesisDTO tesisDTO;
	private TesisDTO licSeleccionada;
	private List<TesisDTO> lista;

	public TesisController() {

		inicializar();

	}

	public void actualizarDemo(TesisDTO datos) {
		System.out.println(datos.getProyecto());
		System.out.println(datos.getNivel());
		System.out.println(datos.getFechatitulacion());
		System.out.println(datos.getAlumno());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		TesisDAO dao = new TesisDAO();
		tesisDTO = new TesisDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new TesisDTO();

	}

	public void guardar() {
		TesisDAO asistenciacursosDAO = new TesisDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(tesisDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		TesisDAO asistenciacursosDao = new TesisDAO();

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
		TesisDAO asistenciacursosDao = new TesisDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

	}

	public TesisDTO getTesisDTO() {
		return tesisDTO;
	}

	public void setTesisDTO(TesisDTO tesisDTO) {
		this.tesisDTO = tesisDTO;
	}

	public TesisDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(TesisDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<TesisDTO> getLista() {
		return lista;
	}

	public void setLista(List<TesisDTO> lista) {
		this.lista = lista;
	}

}
