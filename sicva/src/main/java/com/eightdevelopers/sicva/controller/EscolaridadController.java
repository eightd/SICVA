package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.EscolaridadDAO;
import com.eightdevelopers.sicva.dto.EscolaridadDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Escolaridad' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Escolaridad")
@SessionScoped
// @ViewScoped
public class EscolaridadController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private EscolaridadDTO escolaridaDTO;
	private EscolaridadDTO licSeleccionada;
	private List<EscolaridadDTO> lista;

	public EscolaridadController() {

		inicializar();

	}

	public void actualizarDemo(EscolaridadDTO datos) {
		System.out.println(datos.getNivel());
		System.out.println(datos.getInstitucion());
		System.out.println(datos.getPeriodo());
		System.out.println(datos.getLugar());
		System.out.println(datos.getDocumento());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		EscolaridadDAO dao = new EscolaridadDAO();
		escolaridaDTO = new EscolaridadDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new EscolaridadDTO();

	}

	public void guardar() {
		EscolaridadDAO asistenciacursosDAO = new EscolaridadDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(escolaridaDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		EscolaridadDAO asistenciacursosDao = new EscolaridadDAO();

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
		EscolaridadDAO asistenciacursosDao = new EscolaridadDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

	}

	public EscolaridadDTO getescolaridaDTO() {
		return escolaridaDTO;
	}

	public void setAsistenciacursosDTO(EscolaridadDTO asistenciacursosDTO) {
		this.escolaridaDTO = asistenciacursosDTO;
	}

	public EscolaridadDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(EscolaridadDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<EscolaridadDTO> getLista() {
		return lista;
	}

	public void setLista(List<EscolaridadDTO> lista) {
		this.lista = lista;
	}

}
