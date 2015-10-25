package com.eightdevelopers.sicva.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.eightdevelopers.sicva.dao.CarreraDAO;
import com.eightdevelopers.sicva.dto.CarreraDTO;
import com.eightdevelopers.sicva.dto.EstadoCivilDTO;
import com.eightdevelopers.sicva.dto.LicDTO;
import com.eightdevelopers.sicva.dto.NivelDTO;

import java.io.Serializable;
import java.util.List;


@ManagedBean(name = "CarreraControlador")
@SessionScoped

public class CarreraControlador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<CarreraDTO> listaCarrera;
	private List<EstadoCivilDTO> listaEstadoCivil;
	private List<NivelDTO>	listaNivel;
	private List<LicDTO>	listaLic;

	public CarreraControlador() {
	
		inicializar();
	
	}
	
	public void inicializar() {
		
		System.out.println("metodo inicializar");
		CarreraDAO carreraDAO = new CarreraDAO();
		listaCarrera = carreraDAO.retrieveAlmacen();
		listaEstadoCivil= carreraDAO.estadoCivil();
		listaLic= carreraDAO.lic();
		setListaNivel(carreraDAO.nivel());
	}

	public final List<CarreraDTO> getListaCarrera() {
			return listaCarrera;
	}

	public final void setListaCarrera(List<CarreraDTO> listaCarrera) {
		this.listaCarrera = listaCarrera;
	}

	public List<EstadoCivilDTO> getListaEstadoCivil() {
		return listaEstadoCivil;
	}

	public void setListaEstadoCivil(List<EstadoCivilDTO> listaEstadoCivil) {
		this.listaEstadoCivil = listaEstadoCivil;
	}

	public List<NivelDTO> getListaNivel() {
		return listaNivel;
	}

	public void setListaNivel(List<NivelDTO> listaNivel) {
		this.listaNivel = listaNivel;
	}

	public List<LicDTO> getListaLic() {
		return listaLic;
	}

	public void setListaLic(List<LicDTO> listaLic) {
		this.listaLic = listaLic;
	}
}
