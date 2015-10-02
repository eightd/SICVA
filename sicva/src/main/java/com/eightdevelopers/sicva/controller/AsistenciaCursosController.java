package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

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
// @ViewScoped
public class AsistenciaCursosController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private AsistenciaCursosDTO asistenciacursosDTO;
	private AsistenciaCursosDTO licSeleccionada;
	private List<AsistenciaCursosDTO> lista;

	public AsistenciaCursosController() {

		inicializar();

	}

	public void actualizarDemo(AsistenciaCursosDTO datos) {
		System.out.println(datos.getPeriodo());
		System.out.println(datos.getCurso());
		System.out.println(datos.getInstitucion());
		System.out.println(datos.getLugar());
		System.out.println(datos.getHoras());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		AsistenciaCursosDAO dao = new AsistenciaCursosDAO();
		asistenciacursosDTO = new AsistenciaCursosDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new AsistenciaCursosDTO();

	}

	public void guardar() {
		AsistenciaCursosDAO asistenciacursosDAO = new AsistenciaCursosDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(asistenciacursosDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		AsistenciaCursosDAO asistenciacursosDao = new AsistenciaCursosDAO();

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
		AsistenciaCursosDAO asistenciacursosDao = new AsistenciaCursosDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

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
}
