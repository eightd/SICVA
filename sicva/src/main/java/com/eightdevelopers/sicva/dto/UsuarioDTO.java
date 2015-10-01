package com.eightdevelopers.sicva.dto;
/**
 * DTO utilizado en el inico de sesion
 * @author Leonardo Zavala Torres
 * @version 1.0 */
public class UsuarioDTO {
private String usuario;
private String pass;
private Integer rol;
private Integer id;
private String nombre;
private String ap;
private String am;
private Integer contra_estado;
private Integer id_lic;

public Integer getId_lic() {
	return id_lic;
}
public void setId_lic(Integer id_lic) {
	this.id_lic = id_lic;
}
public Integer getContra_estado() {
	return contra_estado;
}
public void setContra_estado(Integer contra_estado) {
	this.contra_estado = contra_estado;
}
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
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public Integer getRol() {
	return rol;
}
public void setRol(Integer rol) {
	this.rol = rol;
}
}
