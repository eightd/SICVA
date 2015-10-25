package com.eightdevelopers.sicva.dto;

public class CarreraDTO {

	private int id;
	private String descripcion;
	private int nPublicaciones;
	private String contenido;
	private String autor;
	private String asesor;
	
	public final String getAsesor() {
		return asesor;
	}
	public final void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public final String getContenido() {
		return contenido;
	}
	public final void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public final String getAutor() {
		return autor;
	}
	public final void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public final int getnPublicaciones() {
		return nPublicaciones;
	}
	public final void setnPublicaciones(int nPublicaciones) {
		this.nPublicaciones = nPublicaciones;
	}
	
	
	
}
