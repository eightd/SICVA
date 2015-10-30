package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import org.primefaces.model.UploadedFile;

public class SinodalDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Sinodal' de la Base de Datos
	 * 'SICVA'
	 * 
	 * @author Fernando Torres y Carlos Ricardo Hernández Reyes
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String proyecto;
	private Integer nivel;
	private String fechatitulacion;
	private String alumno;
	// se cambia el tipo para la Evidencia de StreamedContent a UploadedFile
	private UploadedFile evidencia;
	private Integer idmodif;
	private Integer idlic;
	private Integer idopc;
	private Integer idusuario;
	private Integer id_nivel;
	private String descripcion;
	private Integer id_nivel_Opcion;
	private String descripcionTitulacion;
	private String descripcionlic;
	private String nombreodif;
	private String existencia;
	private String fechamodif;
	// tipos de datos para uso exclusivo de una secretaria
	private String nombre;
	private String rol;

	public Integer getId_nivel() {
		return id_nivel;
	}

	public void setId_nivel(Integer id_nivel) {
		this.id_nivel = id_nivel;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_nivel_Opcion() {
		return id_nivel_Opcion;
	}

	public void setId_nivel_Opcion(Integer id_nivel_Opcion) {
		this.id_nivel_Opcion = id_nivel_Opcion;
	}

	public String getDescripcionTitulacion() {
		return descripcionTitulacion;
	}

	public void setDescripcionTitulacion(String descripcionTitulacion) {
		this.descripcionTitulacion = descripcionTitulacion;
	}

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

	public Integer getIdopc() {
		return idopc;
	}

	public void setIdopc(Integer idopc) {
		this.idopc = idopc;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
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
