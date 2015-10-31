package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@ManagedBean
public class DatosPersonalesDTO implements Serializable {

	/**
	 * Clase que permite hacer el CRUD de la tabla 'Datos_Persinales' de la Base
	 * de Datos 'SICVA'
	 * 
	 * @author Fernando Torres y Carlos Ricardo Hernández Reyes
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private String ap;
	private String am;
	private String curp;
	private String rfc;
	private String fechanacimiento;
	private String nacionalidad;
	private String domicilio;
	private String cel;
	private String tel;
	private String email;
	private String facebook;
	// se cambia el tipo para la foto de StreamedContent a UploadedFile
	private UploadedFile foto;
	private Integer idmodif;
	private Integer idlic;
	private Integer idestadocivil;
	private Integer idusuario;
	private String nombreodif;
	private String fechamodif;
	private String existencia;
	private Integer idGrado;
	// tipos de datos para uso exclusivo de una secretaria
	private String name;
	private String rol;
    
	private StreamedContent imagen;
	
	
	public StreamedContent getImagen() {
		return imagen;
	}

	public void setImagen(StreamedContent imagen) {
		this.imagen = imagen;
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

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(String fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public Integer getIdestadocivil() {
		return idestadocivil;
	}

	public void setIdestadocivil(Integer idestadocivil) {
		this.idestadocivil = idestadocivil;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
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

	public Integer getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}

}
