package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class MiembroDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Miembro' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String periodo;
	private String organizacion;
	private String nombramiento;

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

	public String getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(String organizacion) {
		this.organizacion = organizacion;
	}

	public String getNombramiento() {
		return nombramiento;
	}

	public void setNombramiento(String nombramiento) {
		this.nombramiento = nombramiento;
	}

}
