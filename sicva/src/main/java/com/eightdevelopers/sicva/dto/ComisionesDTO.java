package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.StreamedContent;

public class ComisionesDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Comisiones' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fecha;
	private String evento;
	private String institucion;
	private String lugar;
	private String participacion;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getParticipacion() {
		return participacion;
	}

	public void setParticipacion(String participacion) {
		this.participacion = participacion;
	}

}
