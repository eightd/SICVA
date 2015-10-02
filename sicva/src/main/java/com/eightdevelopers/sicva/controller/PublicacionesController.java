package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.PublicacionesDAO;
import com.eightdevelopers.sicva.dto.PublicacionesDTO;

@ManagedBean(name = "Publicaciones")
@SessionScoped
// @ViewScoped
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

	public PublicacionesController() {

		inicializar();

	}

	public void actualizarDemo(PublicacionesDTO datos) {
		System.out.println(datos.getPeriodo());
		System.out.println(datos.getPublicacion());
		System.out.println(datos.getInstitucion());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		PublicacionesDAO dao = new PublicacionesDAO();
		publicacionesDTO = new PublicacionesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new PublicacionesDTO();

	}

	public void guardar() {
		PublicacionesDAO asistenciacursosDAO = new PublicacionesDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(publicacionesDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		PublicacionesDAO asistenciacursosDao = new PublicacionesDAO();

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
		PublicacionesDAO asistenciacursosDao = new PublicacionesDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

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

}
