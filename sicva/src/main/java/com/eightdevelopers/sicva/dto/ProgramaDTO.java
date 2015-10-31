package com.eightdevelopers.sicva.dto;

import java.io.Serializable;



public class ProgramaDTO implements Serializable {


	private static final long serialVersionUID = 1L;
	

	
	
	private Integer idprograma;
		private Integer idmateria;
	private Integer idusuario;
	private Integer iddatos;
	private String nivel;
	private String clave;
	private String seriacion;
	private String fechaelaboracion;
	private String fechaactualizacion;
	private Integer horasteoricas;
	private Integer horaspracticas;
	private Integer horasindependientes;
	private Integer totalhoras;
	private Integer creditos;
	private Integer idprogramaprincipal;
	
	/////////////VARIABLES BLOQUE
	
	private Integer idbloque;
	private Integer numerobloque;
	private String fechai;
	private String fechaf;
	private String fechae;
	private String temas;
	private Integer horasestimadas;
	private String siatuaciones;
	private String recursos;
	private String estrategias;
	private Integer idproB;
	
	/////////////////////////////varibales Unidad
	
	private Integer idunidad;

	private String campo;
	private String problema;
	private String competencias;
	private String proposito;
	private Integer idprogramaubica;
	
	//////////variables proposito
	
	private Integer idproposito;
	private String declarativo;
	private String procedimental;
	private String valoral;
	private Integer idprogramapro;
	
	
	////variables criterios
	private Integer idcriterio;
	private String descripcioncriterio;
	private Integer idprogramacriterio;
	////bibliografias
	
	private Integer idbibli;
	private String bibliografia;
	private String referencia;
	private Integer idprogramablibli;
	
	///variable extra para listado programa analitico
	
	private String  descripcionmateriapro;
	
	
	
	public Integer getIdprograma() {
		return idprograma;
	}
	public void setIdprograma(Integer idprograma) {
		this.idprograma = idprograma;
	}
	public Integer getIdmateria() {
		return idmateria;
	}
	public void setIdmateria(Integer idmateria) {
		this.idmateria = idmateria;
	}
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public Integer getIddatos() {
		return iddatos;
	}
	public void setIddatos(Integer iddatos) {
		this.iddatos = iddatos;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getSeriacion() {
		return seriacion;
	}
	public void setSeriacion(String seriacion) {
		this.seriacion = seriacion;
	}
	public String getFechaelaboracion() {
		return fechaelaboracion;
	}
	public void setFechaelaboracion(String fechaelaboracion) {
		this.fechaelaboracion = fechaelaboracion;
	}
	public String getFechaactualizacion() {
		return fechaactualizacion;
	}
	public void setFechaactualizacion(String fechaactualizacion) {
		this.fechaactualizacion = fechaactualizacion;
	}
	public Integer getHorasteoricas() {
		return horasteoricas;
	}
	public void setHorasteoricas(Integer horasteoricas) {
		this.horasteoricas = horasteoricas;
	}
	public Integer getHoraspracticas() {
		return horaspracticas;
	}
	public void setHoraspracticas(Integer horaspracticas) {
		this.horaspracticas = horaspracticas;
	}
	public Integer getHorasindependientes() {
		return horasindependientes;
	}
	public void setHorasindependientes(Integer horasindependientes) {
		this.horasindependientes = horasindependientes;
	}
	public Integer getTotalhoras() {
		return totalhoras;
	}
	public void setTotalhoras(Integer totalhoras) {
		this.totalhoras = totalhoras;
	}
	public Integer getCreditos() {
		return creditos;
	}
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	public Integer getIdprogramaprincipal() {
		return idprogramaprincipal;
	}
	public void setIdprogramaprincipal(Integer idprogramaprincipal) {
		this.idprogramaprincipal = idprogramaprincipal;
	}
	public Integer getIdbloque() {
		return idbloque;
	}
	public void setIdbloque(Integer idbloque) {
		this.idbloque = idbloque;
	}
	public Integer getNumerobloque() {
		return numerobloque;
	}
	public void setNumerobloque(Integer numerobloque) {
		this.numerobloque = numerobloque;
	}
	public String getFechai() {
		return fechai;
	}
	public void setFechai(String fechai) {
		this.fechai = fechai;
	}
	public String getFechaf() {
		return fechaf;
	}
	public void setFechaf(String fechaf) {
		this.fechaf = fechaf;
	}
	public String getFechae() {
		return fechae;
	}
	public void setFechae(String fechae) {
		this.fechae = fechae;
	}
	public String getTemas() {
		return temas;
	}
	public void setTemas(String temas) {
		this.temas = temas;
	}
	public Integer getHorasestimadas() {
		return horasestimadas;
	}
	public void setHorasestimadas(Integer horasestimadas) {
		this.horasestimadas = horasestimadas;
	}
	public String getSiatuaciones() {
		return siatuaciones;
	}
	public void setSiatuaciones(String siatuaciones) {
		this.siatuaciones = siatuaciones;
	}
	public String getRecursos() {
		return recursos;
	}
	public void setRecursos(String recursos) {
		this.recursos = recursos;
	}
	public String getEstrategias() {
		return estrategias;
	}
	public void setEstrategias(String estrategias) {
		this.estrategias = estrategias;
	}
	public Integer getIdproB() {
		return idproB;
	}
	public void setIdproB(Integer idproB) {
		this.idproB = idproB;
	}
	public Integer getIdunidad() {
		return idunidad;
	}
	public void setIdunidad(Integer idunidad) {
		this.idunidad = idunidad;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getProblema() {
		return problema;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	public String getCompetencias() {
		return competencias;
	}
	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}
	public String getProposito() {
		return proposito;
	}
	public void setProposito(String proposito) {
		this.proposito = proposito;
	}
	public Integer getIdprogramaubica() {
		return idprogramaubica;
	}
	public void setIdprogramaubica(Integer idprogramaubica) {
		this.idprogramaubica = idprogramaubica;
	}
	public Integer getIdproposito() {
		return idproposito;
	}
	public void setIdproposito(Integer idproposito) {
		this.idproposito = idproposito;
	}
	public String getDeclarativo() {
		return declarativo;
	}
	public void setDeclarativo(String declarativo) {
		this.declarativo = declarativo;
	}
	public String getProcedimental() {
		return procedimental;
	}
	public void setProcedimental(String procedimental) {
		this.procedimental = procedimental;
	}
	public String getValoral() {
		return valoral;
	}
	public void setValoral(String valoral) {
		this.valoral = valoral;
	}
	public Integer getIdprogramapro() {
		return idprogramapro;
	}
	public void setIdprogramapro(Integer idprogramapro) {
		this.idprogramapro = idprogramapro;
	}
	public Integer getIdcriterio() {
		return idcriterio;
	}
	public void setIdcriterio(Integer idcriterio) {
		this.idcriterio = idcriterio;
	}
	public String getDescripcioncriterio() {
		return descripcioncriterio;
	}
	public void setDescripcioncriterio(String descripcioncriterio) {
		this.descripcioncriterio = descripcioncriterio;
	}
	public Integer getIdprogramacriterio() {
		return idprogramacriterio;
	}
	public void setIdprogramacriterio(Integer idprogramacriterio) {
		this.idprogramacriterio = idprogramacriterio;
	}
	public Integer getIdbibli() {
		return idbibli;
	}
	public void setIdbibli(Integer idbibli) {
		this.idbibli = idbibli;
	}
	public String getBibliografia() {
		return bibliografia;
	}
	public void setBibliografia(String bibliografia) {
		this.bibliografia = bibliografia;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Integer getIdprogramablibli() {
		return idprogramablibli;
	}
	public void setIdprogramablibli(Integer idprogramablibli) {
		this.idprogramablibli = idprogramablibli;
	}
	public String getDescripcionmateriapro() {
		return descripcionmateriapro;
	}
	public void setDescripcionmateriapro(String descripcionmateriapro) {
		this.descripcionmateriapro = descripcionmateriapro;
	}


	


	

}
