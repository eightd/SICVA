package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

/**
 * DTO correspondiente a la tabla 'Usuarios' de la Base de Datos 'SICVA'
 * 
 * @author Toxik
 *
 */

public class UsuariosDTO implements Serializable {
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
	private Integer idlic;
	private String des_lic;
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

	public String getDesc_rol() {
		return desc_rol;
	}

	public void setDesc_rol(String desc_rol) {
		this.desc_rol = desc_rol;
	}

	public Integer getIdlic() {
		return idlic;
	}

	public void setIdlic(Integer idlic) {
		this.idlic = idlic;
	}

	public String getDes_lic() {
		return des_lic;
	}

	public void setDes_lic(String des_lic) {
		this.des_lic = des_lic;
	}

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public Integer getContra_estado() {
		return contra_estado;
	}

	public void setContra_estado(Integer contra_estado) {
		this.contra_estado = contra_estado;
	}

}
