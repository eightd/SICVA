package com.eightdevelopers.sicva.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.dao.VinculacionesDAO;
import com.eightdevelopers.sicva.dto.VinculacionDTO;

@ManagedBean(name = "vinculacion")
@SessionScoped
public class VinculacionesController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<VinculacionDTO> listaDocentes;
	private VinculacionDTO seleccionD;
	private List<VinculacionDTO> filtros;
	
	public VinculacionesController(){
		inicializar();
	}
	
	@PostConstruct
	public void init() {
		inicializar();
	}
	
	public void inicializar() {
		
		VinculacionesDAO vinc = new VinculacionesDAO();
		listaDocentes=vinc.listarDocentes();
		seleccionD = new VinculacionDTO();
	}

	
	

	
	public void vincular() {
		
      VinculacionesDAO vinc = new VinculacionesDAO();
      String resultado = vinc.VincularDocente(seleccionD);
  
      
      	if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Ya se ha vinculado"));
			
		}

		inicializar();

	}
	

	
	public void vincularDocente(){
		System.out.println("vinculado "+seleccionD.getNombre());
	}

	public List<VinculacionDTO> getListaDocentes() {
		return listaDocentes;
	}

	public void setListaDocentes(List<VinculacionDTO> listaDocentes) {
		this.listaDocentes = listaDocentes;
	}

	public VinculacionDTO getSeleccionD() {
		return seleccionD;
	}

	public void setSeleccionD(VinculacionDTO seleccionD) {
		this.seleccionD = seleccionD;
	}

	public List<VinculacionDTO> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<VinculacionDTO> filtros) {
		this.filtros = filtros;
	}
	
	
	}
