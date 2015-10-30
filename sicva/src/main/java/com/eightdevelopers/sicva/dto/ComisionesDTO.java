package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class ComisionesDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Comisiones' de la Base de
	 * Datos 'SICVA'
	 * 
	 * @author Fernando Torres y Carlos Ricardo Hernández Reyes
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fecha;
	private String evento;
	private String institucion;
	private String lugar;
	private String participacion;
	// se cambia el tipo para la Evidencia de StreamedContent a UploadedFile
	private UploadedFile evidencia;
	private Integer idmodif;
	private Integer idlic;
	private Integer idusuario;
	private String descripcionlic;
	private String nombreodif;
	private String existencia;
	private String fechamodif;
	
	// tipos de datos para uso exclusivo de una secretaria
	private String name;
	private String rol;

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

	public UploadedFile getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(UploadedFile evidencia) {
		this.evidencia = evidencia;
	}

	public String getDescripcionlic() {
		return descripcionlic;
	}

	public void setDescripcionlic(String descripcionlic) {
		this.descripcionlic = descripcionlic;
	}

	public String getNombreodif() {
		return nombreodif;
	}

	public void setNombreodif(String nombreodif) {
		this.nombreodif = nombreodif;
	}

	public String getExistencia() {
		return existencia;
	}

	public void setExistencia(String existencia) {
		this.existencia = existencia;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getFechamodif() {
		return fechamodif;
	}

	public void setFechamodif(String fechamodif) {
		this.fechamodif = fechamodif;
	}

}
