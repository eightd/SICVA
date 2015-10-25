package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

public class DocentesDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String ap;
	private String am;
	private String usuario;
	private String contra;
	private Integer contra_estado;
	private Integer idrol;
	private String desc_rol;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAp() {
		return ap;
	}
	public void setAp(String ap) {
		this.ap = ap;
	}
	public String getAm() {
		return am;
	}
	public void setAm(String am) {
		this.am = am;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContra() {
		return contra;
	}
	public void setContra(String contra) {
		this.contra = contra;
	}
	public Integer getContra_estado() {
		return contra_estado;
	}
	public void setContra_estado(Integer contra_estado) {
		this.contra_estado = contra_estado;
	}
	public Integer getIdrol() {
		return idrol;
	}
	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}
	public String getDesc_rol() {
		return desc_rol;
	}
	public void setDesc_rol(String desc_rol) {
		this.desc_rol = desc_rol;
	}
	

}
