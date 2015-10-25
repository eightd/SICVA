package com.eightdevelopers.sicva.dto;

import java.io.Serializable;

/**
 * DTO correspondiente a la tabla 'licenciaturas' de la Base de Datos 'SICVA'
 * 
 * @author Toxik
 *
 */

public class LicenciaturaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
