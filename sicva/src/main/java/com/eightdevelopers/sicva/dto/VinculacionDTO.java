package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

public class VinculacionDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id_usuario;
	private String nombre;
	private String ap;
	private String am;
	private Integer id_licenciatura;
	private String desc_rol;
	
	
	
	public Integer getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
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
	public Integer getId_licenciatura() {
		return id_licenciatura;
	}
	public void setId_licenciatura(Integer id_licenciatura) {
		this.id_licenciatura = id_licenciatura;
	}
	public String getDesc_rol() {
		return desc_rol;
	}
	public void setDesc_rol(String desc_rol) {
		this.desc_rol = desc_rol;
	}
	
	
	
	
	
	

}