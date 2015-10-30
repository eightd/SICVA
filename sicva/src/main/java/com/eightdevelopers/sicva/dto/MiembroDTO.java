package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class MiembroDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Miembro' de la Base de Datos
	 * 'SICVA'
	 * 
	 * @author Fernando Torres y Carlos Ricardo Hernández Reyes
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String periodo;
	private String organizacion;
	private String nombramiento;
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
	private String nombre;
	private String rol;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
