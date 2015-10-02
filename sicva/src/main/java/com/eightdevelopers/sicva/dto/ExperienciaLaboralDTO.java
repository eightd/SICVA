package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class ExperienciaLaboralDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Experiencia_Laboral' de la
	 * Base de Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String periodo;
	private String institucion;
	private String dpto;
	private String lugar;
	private String puesto;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
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

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

}
