package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class PublicacionesDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Publicaciones' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String periodo;
	private String publicacion;
	private String institucion;

	private StreamedContent evidencia;
	private Integer idmodif;
	private Integer idlic;
	private Integer idusuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public StreamedContent getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(StreamedContent evidencia) {
		this.evidencia = evidencia;
	}

	public Integer getIdmodif() {
		return idmodif;
	}

	public void setIdmodif(Integer idmodif) {
		this.idmodif = idmodif;
	}

	public Integer getIdlic() {
		return idlic;
	}

	public void setIdlic(Integer idlic) {
		this.idlic = idlic;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(String publicacion) {
		this.publicacion = publicacion;
	}

}
