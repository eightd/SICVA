package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.SinodalDAO;
import com.eightdevelopers.sicva.dto.SinodalDTO;

@ManagedBean(name = "Sinodal")
@SessionScoped
// @ViewScoped
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

	public SinodalController() {

		inicializar();

	}

	public void actualizarDemo(SinodalDTO datos) {
		System.out.println(datos.getProyecto());
		System.out.println(datos.getNivel());
		System.out.println(datos.getFechatitulacion());
		System.out.println(datos.getAlumno());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdopc());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		SinodalDAO dao = new SinodalDAO();
		sinodalDTO = new SinodalDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new SinodalDTO();

	}

	public void guardar() {
		SinodalDAO asistenciacursosDAO = new SinodalDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(sinodalDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		SinodalDAO asistenciacursosDao = new SinodalDAO();

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
		SinodalDAO asistenciacursosDao = new SinodalDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

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



}
