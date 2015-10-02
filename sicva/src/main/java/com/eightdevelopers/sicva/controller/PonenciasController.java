package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import com.eightdevelopers.sicva.dao.PonenciasDAO;
import com.eightdevelopers.sicva.dto.PonenciasDTO;

@ManagedBean(name = "Ponencia")
@SessionScoped
// @ViewScoped
public class PonenciasController implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Ponencia' de la Base de Datos
	 * 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private PonenciasDTO ponenciasDTO;
	private PonenciasDTO licSeleccionada;
	private List<PonenciasDTO> lista;
	public PonenciasController() {

		inicializar();

	}

	public void actualizarDemo(PonenciasDTO datos) {
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
		PonenciasDAO dao = new PonenciasDAO();
		ponenciasDTO = new PonenciasDTO();
		lista = dao.listarponencias();
		licSeleccionada = new PonenciasDTO();

	}

	public void guardar() {
		PonenciasDAO asistenciacursosDAO = new PonenciasDAO();
		String resultado = "";
		resultado = asistenciacursosDAO.guardarDato(ponenciasDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
	}

	public void actualizar() {

		PonenciasDAO ponenciasDao = new PonenciasDAO();

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
		PonenciasDAO asistenciacursosDao = new PonenciasDAO();
		String resultado = asistenciacursosDao.EliminarAsistenciaCurso(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();

	}

	public PonenciasDTO getPonenciasDTO() {
		return ponenciasDTO;
	}

	public void setPonenciasDTO(PonenciasDTO ponenciasDTO) {
		this.ponenciasDTO = ponenciasDTO;
	}

	public PonenciasDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(PonenciasDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<PonenciasDTO> getLista() {
		return lista;
	}

	public void setLista(List<PonenciasDTO> lista) {
		this.lista = lista;
	}
}
	