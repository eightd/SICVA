package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.MiembroDAO;
import com.eightdevelopers.sicva.dto.MiembroDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Miembro' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

@ManagedBean(name = "Miembro")
@SessionScoped
// @ViewScoped
public class MiembroController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private MiembroDTO miembroDTO;
	private MiembroDTO licSeleccionada;
	private List<MiembroDTO> lista;

	public MiembroController() {

		inicializar();

	}

	public void actualizarDemo(MiembroDTO datos) {
		System.out.println(datos.getPeriodo());
		System.out.println(datos.getOrganizacion());
		System.out.println(datos.getNombramiento());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		MiembroDAO dao = new MiembroDAO();
       miembroDTO = new MiembroDTO();
		lista = dao.listarasistenciacursos();
		licSeleccionada = new MiembroDTO();

	}

	public void guardar() {
		MiembroDAO asistenciacursosDAO = new MiembroDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(miembroDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		MiembroDAO asistenciacursosDao = new MiembroDAO();

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
		MiembroDAO asistenciacursosDao = new MiembroDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

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

	
	}
