package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class TesisDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proyecto;
	private Integer nivel;
	private String fechatitulacion;
	private String alumno;
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

	public String getProyecto() {
		return proyecto;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public String getFechatitulacion() {
		return fechatitulacion;
	}

	public void setFechatitulacion(String fechatitulacion) {
		this.fechatitulacion = fechatitulacion;
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

}
