package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.ExperienciaLaboralDAO;
import com.eightdevelopers.sicva.dto.ExperienciaLaboralDTO;
/**
 * Clase que permite hacer el CRUD de la tabla 'Experiencia_Laboral' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */


@ManagedBean(name = "Experiencia")
@SessionScoped
// @ViewScoped
public class ExperienciaLaboralController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private ExperienciaLaboralDTO experienciaDTO;
	private ExperienciaLaboralDTO licSeleccionada;
	private List<ExperienciaLaboralDTO> lista;
	public ExperienciaLaboralController() {

		inicializar();

	}

	public void actualizarDemo(ExperienciaLaboralDTO datos) {
		System.out.println(datos.getPeriodo());
		System.out.println(datos.getInstitucion());
		System.out.println(datos.getDpto());
		System.out.println(datos.getLugar());
		System.out.println(datos.getPuesto());
		System.out.println(datos.getEvidencia());
		System.out.println(datos.getIdmodif());
		System.out.println(datos.getIdlic());
		System.out.println(datos.getIdusuario());

		licSeleccionada = datos;

	}

	public void inicializar() {
		ExperienciaLaboralDAO dao = new ExperienciaLaboralDAO();
		experienciaDTO = new ExperienciaLaboralDTO();
		lista = dao.listarexperiencia();
		licSeleccionada = new ExperienciaLaboralDTO();

	}

	public void guardar() {
		ExperienciaLaboralDAO asistenciacursosDAO = new ExperienciaLaboralDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(experienciaDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		ExperienciaLaboralDAO ponenciasDao = new ExperienciaLaboralDAO();

	//	System.out.println(licSeleccionada.getId());

		String resultado = ponenciasDao.ActualizarLicenciatura(licSeleccionada);
		if(resultado!= ""){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Acción exitosa ", resultado));
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Acción denegada ", "Fallo al actualizar"));
			
		}
		inicializar();
	}

	public void imprimir() {

		System.out.println("Hola mndo");
	}

	public void eliminar() {
		ExperienciaLaboralDAO asistenciacursosDao = new ExperienciaLaboralDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

	}

	public ExperienciaLaboralDTO getExperienciaDTO() {
		return experienciaDTO;
	}

	public void setExperienciaDTO(ExperienciaLaboralDTO experienciaDTO) {
		this.experienciaDTO = experienciaDTO;
	}

	public ExperienciaLaboralDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(ExperienciaLaboralDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<ExperienciaLaboralDTO> getLista() {
		return lista;
	}

	public void setLista(List<ExperienciaLaboralDTO> lista) {
		this.lista = lista;
	}

	
}
	