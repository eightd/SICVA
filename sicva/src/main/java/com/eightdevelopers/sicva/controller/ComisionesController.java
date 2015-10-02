package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

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
// @ViewScoped
public class ComisionesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private ComisionesDTO comisionesDTO;
	private ComisionesDTO licSeleccionada;
	private List<ComisionesDTO> lista;

	public ComisionesController() {

		inicializar();

	}

	public void actualizarDemo(ComisionesDTO datos) {
		System.out.println(datos.getFecha());
		System.out.println(datos.getEvento());
		System.out.println(datos.getInstitucion());
		System.out.println(datos.getLugar());
		System.out.println(datos.getParticipacion());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		ComisionesDAO dao = new ComisionesDAO();
		comisionesDTO = new ComisionesDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new ComisionesDTO();

	}

	public void guardar() {
		ComisionesDAO asistenciacursosDAO = new ComisionesDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(comisionesDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		ComisionesDAO asistenciacursosDao = new ComisionesDAO();

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
		ComisionesDAO asistenciacursosDao = new ComisionesDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

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
