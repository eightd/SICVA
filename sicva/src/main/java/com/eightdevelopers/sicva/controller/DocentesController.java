package com.eightdevelopers.sicva.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.eightdevelopers.sicva.dao.DocentesDAO;
import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.DocentesDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Clase que permite obtener los datos de vista para registrar un nuevo docente
 * en la tabla 'usuarios' de la BD SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */

@ManagedBean(name = "Docentes")
@SessionScoped
public class DocentesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DocentesDTO docente;
	private DocentesDTO docenteSeleccionado;
	private List<DocentesDTO> listadocen;
	private List<DocentesDTO> listadocenLic;
	private List<DocentesDTO> filtros;

	public DocentesController() {
		inicializar();
	}

	/**
	 * Método que obtiene los datos para actualizar los docentes de la tabla
	 * 'usuarios' de la tabla BD SICVA
	 * 
	 * @param datos
	 */
	public void actualizarDemo(DocentesDTO datos) {

		docenteSeleccionado = datos;
	}

	public void inicializar() {
		DocentesDAO dao = new DocentesDAO();
		docente = new DocentesDTO();
		listadocen = dao.listarDocentes();
		listadocenLic = dao.listarDocentesLic();
		docenteSeleccionado = new DocentesDTO();
	}

	/**
	 * Método que permite instanciar y acceder al método GuardarUsuario de tipo
	 * DAO
	 */
	public void guardar() {
		DocentesDAO docentesDAO = new DocentesDAO();
		String resultado = "";

		resultado = docentesDAO.GuardarDocente(docente);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Usuario duplicado"));

		}

		inicializar();
	}

	/**
	 * Método que permite instanciar y acceder al método ActualizarDocente de
	 * tipo DAO
	 */
	public void actualizar() {

		DocentesDAO docentesDAO = new DocentesDAO();
		String resultado = docentesDAO.ActualizarDocen(docenteSeleccionado);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Usuario duplicado"));

		}

		inicializar();
	}

	/**
	 * Método que permite instanciar y acceder al método EliminarDocente de tipo
	 * DAO
	 */
	public void exportarPDF() throws JRException, IOException{
		Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("id",""+docenteSeleccionado.getId());
		ConexionBD conexionBD = new ConexionBD();
		 conexionBD.abrir();
		Connection conn = conexionBD.getConexion();
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reporte.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, conn);
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=Reporte.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void eliminar() {
		System.out.println("prueba "+docenteSeleccionado.getId());
		DocentesDAO docentesDAO = new DocentesDAO();
		String resultado = docentesDAO.EliminarDocen(docenteSeleccionado);    
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al eliminar"));

		}

		inicializar();

	}
	
	/**
	 * Método que permite instanciar y acceder al método Resetear contraseña de tipo DAO
	 */
	public void reset() {

		DocentesDAO docentesDAO = new DocentesDAO();
		String resultado = docentesDAO.ResetContra(docenteSeleccionado);      
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al resetear contraseña"));

		}

		inicializar();

	}

	public DocentesDTO getDocente() {
		return docente;
	}

	public void setDocente(DocentesDTO docente) {
		this.docente = docente;
	}

	public List<DocentesDTO> getListadocen() {
		return listadocen;
	}

	public void setListadocen(List<DocentesDTO> listadocen) {
		this.listadocen = listadocen;
	}

	public DocentesDTO getDocenteSeleccionado() {
		return docenteSeleccionado;
	}

	public void setDocenteSeleccionado(DocentesDTO docenteSeleccionado) {
		this.docenteSeleccionado = docenteSeleccionado;
	}

	public List<DocentesDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<DocentesDTO> filtros) {
		this.filtros = filtros;
	}

	public List<DocentesDTO> getListadocenLic() {
		return listadocenLic;
	}

	public void setListadocenLic(List<DocentesDTO> listadocenLic) {
		this.listadocenLic = listadocenLic;
	}

}
