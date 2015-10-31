package com.eightdevelopers.sicva.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

import com.eightdevelopers.sicva.dao.LoginDAO;
import com.eightdevelopers.sicva.dao.MateriasDAO;
import com.eightdevelopers.sicva.dao.ProgramaDAO;
import com.eightdevelopers.sicva.dto.MateriasDTO;
import com.eightdevelopers.sicva.dto.ProgramaDTO;
import com.eightdevelopers.sicva.dto.PropositosDTO;
import com.eightdevelopers.sicva.dto.UsuarioDTO;




@ManagedBean(name = "Programa")
@SessionScoped
// @ViewScoped
public class ProgramaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2715913227853027610L;

	private ProgramaDTO programaDTO;
	private ProgramaDTO proSeleccionada;
	private List<ProgramaDTO> lista;
	private Integer id_session;
	private Integer idprograma;
	private String valorobtenido;
	private List<ProgramaDTO> listaprincipales;
	
	
	//////bloque
	private ProgramaDTO bloqueDTO;
	private ProgramaDTO bloSeleccionada;
	private List<ProgramaDTO> listabloque;
	private List<ProgramaDTO> listabloque2;
	private List<ProgramaDTO> listabloque3;
	
	///////unidad
	private ProgramaDTO ubicacionesDTO;;
	private ProgramaDTO licSeleccionada;
	private List<ProgramaDTO> listaunidad;
	
	/////////////propositos
	private ProgramaDTO propositosDTO;
	private ProgramaDTO propositosSeleccionada;
	private List<ProgramaDTO> listaproposito;
	///criterio

	private ProgramaDTO criteriosDTO;
	private ProgramaDTO criteriosSeleccionada;
	private List<ProgramaDTO> listacriterios;
	/////bibliografias
	private ProgramaDTO bibliografiasDTO;
	private ProgramaDTO bibliografiasSeleccionada;
	private List<ProgramaDTO> listabibliografias;	
	
	
	

	public ProgramaController() {
		inicializar();

	}

	public void actualizarDemo(ProgramaDTO datos) {
		proSeleccionada = datos;
	}
	
	
	//////////bloque
	public void actualizarDemobloque(ProgramaDTO datos) {
		bloSeleccionada = datos;
	}
	
	public void actualizarDemounidad(ProgramaDTO datos) {
		licSeleccionada = datos;
	}
	
	
	public void actualizarDemoproposito(ProgramaDTO datos) {
	  propositosSeleccionada = datos;
	}
	
	public void actualizarDemocriterios(ProgramaDTO datos) {
		  criteriosSeleccionada = datos;
		}
		
	public void actualizarDemobibliografias(ProgramaDTO datos) {
		  bibliografiasSeleccionada = datos;
		}
		

	public void inicializar() {
	
		ProgramaDAO dao = new ProgramaDAO();

		programaDTO = new ProgramaDTO();
		String valor=(obtenerValorSesion("id"));
		id_session=Integer.parseInt(valor);
		
		lista = dao.listarprograma(id_session);/////////LISTA PROGRAMAS ANALITICOS
		proSeleccionada = new ProgramaDTO();//DATO SELECIONADA
		
		///bloque
		bloqueDTO = new ProgramaDTO();
		bloSeleccionada = new ProgramaDTO();
///ubicaciones
	 ubicacionesDTO = new ProgramaDTO();
		licSeleccionada = new ProgramaDTO();
		//propositos
		
		 propositosDTO = new ProgramaDTO();
		propositosSeleccionada = new ProgramaDTO();
		//criterios
		
		 criteriosDTO = new ProgramaDTO();
	     criteriosSeleccionada = new ProgramaDTO();
	     ///////bibliografias
	     
	     bibliografiasDTO = new ProgramaDTO();
	     bibliografiasSeleccionada = new ProgramaDTO();
	     
	     
	
	}
	

	
	
	

	public void guardar() {
	String valor=(obtenerValorSesion("id"));
		id_session=Integer.parseInt(valor);

		programaDTO.setIdusuario(id_session);
		ProgramaDAO DAO = new ProgramaDAO();
		String resultado = "";
		resultado = DAO.guardarPrograma(programaDTO);

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
		
	}

	
	public void guardardatos() {

		programaDTO.setIdprogramaprincipal(Integer.parseInt(valorobtenido));
		
		
		
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarDprincipal(programaDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
//		

		String 	resultado = DAO.guardarDprincipal(programaDTO);

		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		inicializar();
		   ProgramaDAO dao2 = new ProgramaDAO();
		  		
	  		listaprincipales=dao2.listardprincipales(Integer.parseInt(valorobtenido));
	}
	
	public void guardarbloque() {

		bloqueDTO.setIdproB(Integer.parseInt(valorobtenido));

			ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarBloque(bloqueDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
//		

		String 	resultado = DAO.guardarBloque(bloqueDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listabloque=dao2.listarbloques(Integer.parseInt(valorobtenido));
	}
	
	
	public void guardarbloque2() {

		bloqueDTO.setIdproB(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarBloque2(bloqueDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
//		

		String 	resultado = DAO.guardarBloque2(bloqueDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listabloque2=dao2.listarbloques2(Integer.parseInt(valorobtenido));
	}
	public void guardarbloque3() {

		bloqueDTO.setIdproB(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarBloque3(bloqueDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
		
		String resultado = DAO.guardarBloque3(bloqueDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listabloque3=dao2.listarbloques3(Integer.parseInt(valorobtenido));
	}

	public void guardarunidad() {

		ubicacionesDTO.setIdprogramaubica(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarUbicacion(ubicacionesDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
		
		String resultado = DAO.guardarUbicacion(ubicacionesDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		
		
		
		
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listaunidad=dao2.listarubicaciones(Integer.parseInt(valorobtenido));
	}
	
	
	public void guardarproposito() {

		propositosDTO.setIdprogramapro(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarPropositos(propositosDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
//		
		String 	resultado = DAO.guardarPropositos(propositosDTO);
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listaproposito=dao2.listarpropositos(Integer.parseInt(valorobtenido));
	}

	public void guardarcriterios() {

		criteriosDTO.setIdprogramacriterio(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardacriterio(criteriosDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
		
		String 	resultado = DAO.guardacriterio(criteriosDTO);

		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
	
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listacriterios=dao2.listarcriterios(Integer.parseInt(valorobtenido));
	}
	
	public void guardarbibliografias() {///////////////guardarbibliografias

		bibliografiasDTO.setIdprogramablibli(Integer.parseInt(valorobtenido));
		ProgramaDAO DAO = new ProgramaDAO();
//		String resultado = "";
//		resultado = DAO.guardarBibliografias(bibliografiasDTO);
//
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(resultado));
//		
		String 		resultado = DAO.guardarBibliografias(bibliografiasDTO);

		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al guardar"));
		}
		
		inicializar();
	      ProgramaDAO dao2 = new ProgramaDAO();
			listabibliografias=dao2.listarbibliografias(Integer.parseInt(valorobtenido));
	}
	
	
	
	public void actualizar() {/////actualizar datos principales

		ProgramaDAO Dao = new ProgramaDAO();

		// System.out.println(licSeleccionada.getId());

		String resultado = Dao.ActualizarDprincipales(proSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
	}
	
	public void actualizarbloque() {

		ProgramaDAO Dao = new ProgramaDAO();

		// System.out.println(licSeleccionada.getId());

		String resultado = Dao.ActualizarBloque(bloSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
		
		
	    ProgramaDAO dao2 = new ProgramaDAO();
			listabloque=dao2.listarbloques(Integer.parseInt(valorobtenido));
			
	  		listabloque2=dao2.listarbloques2(Integer.parseInt(valorobtenido));
	  		listabloque3=dao2.listarbloques3(Integer.parseInt(valorobtenido));
	}
	
	
	public void actualizarunidad() {

		ProgramaDAO Dao = new ProgramaDAO();

		// System.out.println(licSeleccionada.getId());

		String resultado = Dao.ActualizarUbicaciones(licSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
	}
	
	
	
	
	public void actualizarpropositos() {

		ProgramaDAO Dao = new ProgramaDAO();
		String resultado = Dao.ActualizarPropositos(propositosSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
	}
	
	public void actualizarcriterios() {

		ProgramaDAO Dao = new ProgramaDAO();
		String resultado = Dao.ActualizarCriterios(criteriosSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
		  ProgramaDAO dao2 = new ProgramaDAO();
			listacriterios=dao2.listarcriterios(Integer.parseInt(valorobtenido));
		
	}
	
	public void actualizarbibliografias() {

		ProgramaDAO Dao = new ProgramaDAO();
		String resultado = Dao.ActualizarBibliografias(bibliografiasSeleccionada);
				
		if (resultado != "") {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Acción exitosa ", resultado));

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acción denegada ", "Fallo al actualizar"));

		}
		inicializar();
		  ProgramaDAO dao2 = new ProgramaDAO();
			listabibliografias=dao2.listarbibliografias(Integer.parseInt(valorobtenido));
		
	}
	
	
//
//	public void imprimir() {
//
//		System.out.println("Hola mndo");
//	}
//
	
	
	
	///////metodos eliminar 
public void eliminar() {//////eliminar datos principales
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarDprincipales(proSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();
		
        ProgramaDAO dao2 = new ProgramaDAO();
		
		
		listaprincipales=dao2.listardprincipales(Integer.parseInt(valorobtenido));

	}


public void eliminarbloque() {//////eliminar datos principales
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarBloque(bloSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();	
        ProgramaDAO dao2 = new ProgramaDAO();
		listabloque=dao2.listarbloques(Integer.parseInt(valorobtenido));
		
  		listabloque2=dao2.listarbloques2(Integer.parseInt(valorobtenido));
  		listabloque3=dao2.listarbloques3(Integer.parseInt(valorobtenido));
	}


public void eliminarunidad() {//////eliminar Unidad
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarUbicaciones(licSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();	
        ProgramaDAO dao2 = new ProgramaDAO();
        
       listaunidad=dao2.listarubicaciones(Integer.parseInt(valorobtenido));	
       }


public void eliminarproposito() {//////eliminar datos proposito
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarPropositos(propositosSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();	
        ProgramaDAO dao2 = new ProgramaDAO();
        
       listaproposito=dao2.listarpropositos(Integer.parseInt(valorobtenido));	
       }

public void eliminarcriterios() {//////eliminar datos CRITERIOS
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarCriterios(criteriosSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();	
        ProgramaDAO dao2 = new ProgramaDAO();
        
       listacriterios=dao2.listarcriterios(Integer.parseInt(valorobtenido));	
       }

public void eliminarbibliografias() {//////eliminar datos CRITERIOS
	ProgramaDAO materiasDao = new ProgramaDAO();
		String resultado = materiasDao.EliminarBibliografias(bibliografiasSeleccionada);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(resultado));
		inicializar();	
        ProgramaDAO dao2 = new ProgramaDAO();
        
       listabibliografias=dao2.listarbibliografias(Integer.parseInt(valorobtenido));	
       }



	
//	public void demo(String valor){
//		System.out.println("Valor recibido"+valor);
//		String usuario=obtenerValorSesion("id");		
//		System.out.println("UsuarioActual:"+usuario);
//		subirValorSesion("materia"+usuario, valor);
//		System.out.println("----"+obtenerValorSesion("materia"+usuario));
//		
//		redireccionar("DatosPrincipales.xhtml");
//	}
//	
	public void demo(String valor){
	          valorobtenido=valor;
          
          ProgramaDAO dao2 = new ProgramaDAO();
  		  		
  		listaprincipales=dao2.listardprincipales(Integer.parseInt(valorobtenido));
  	
  		listabloque=dao2.listarbloques(Integer.parseInt(valorobtenido));
  		listabloque2=dao2.listarbloques2(Integer.parseInt(valorobtenido));
  		listabloque3=dao2.listarbloques3(Integer.parseInt(valorobtenido));
  		listaunidad=dao2.listarubicaciones(Integer.parseInt(valorobtenido));
  		listaproposito=dao2.listarpropositos(Integer.parseInt(valorobtenido));
  		listacriterios=dao2.listarcriterios(Integer.parseInt(valorobtenido));
  		listabibliografias=dao2.listarbibliografias(Integer.parseInt(valorobtenido));
//		
//		System.out.println("Valor recibido"+valor);
		
         String usuario=obtenerValorSesion("id");		
		System.out.println("UsuarioActual:"+usuario);

		subirValorSesion("materia"+usuario, null);
		
		subirValorSesion("materia"+usuario, valor);
		System.out.println("---este es el valor obtenido -"+obtenerValorSesion("materia"+usuario));
		
		redireccionar("DatosPrincipales.xhtml");
	}
	
	public void cargarIdUsuario(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);

		subirValorSesion("id", listado.get(0).getId() + "");
		System.out.println(obtenerValorSesion("id"));

	}

	public void cargarIdRol(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);

		subirValorSesion("rol", listado.get(0).getRol() + "");
		System.out.println(obtenerValorSesion("rol"));

	}

	public void cargarIdLic(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);

		subirValorSesion("lic", listado.get(0).getId_lic() + "");
		System.out.println(obtenerValorSesion("lic"));

	}
	public void cargarContraes(String nom, String pwd) {
		LoginDAO c = new LoginDAO();
		List<UsuarioDTO> listado = c.isAcountExists(nom, pwd);

		subirValorSesion("ces", listado.get(0).getContra_estado() + "");
		System.out.println(obtenerValorSesion("ces"));

	}
	public void cerrarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().invalidateSession();// Se borrar
																// valores en
																// sesion
			redireccionar("index.xhtml");
		} catch (Exception ex) {

		}
	}

	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	public void subirValorSesion(String clave, String valor) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put(clave, valor);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public void redireccionar(String ruta) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect(ruta);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ProgramaDTO getProgramaDTO() {
		return programaDTO;
	}

	public void setProgramaDTO(ProgramaDTO programaDTO) {
		this.programaDTO = programaDTO;
	}

	public ProgramaDTO getProSeleccionada() {
		return proSeleccionada;
	}

	public void setProSeleccionada(ProgramaDTO proSeleccionada) {
		this.proSeleccionada = proSeleccionada;
	}

	public List<ProgramaDTO> getLista() {
		return lista;
	}

	public void setLista(List<ProgramaDTO> lista) {
		this.lista = lista;
	}

	public Integer getId_session() {
		return id_session;
	}

	public void setId_session(Integer id_session) {
		this.id_session = id_session;
	}

	public String getValorobtenido() {
		return valorobtenido;
	}

	public void setValorobtenido(String valorobtenido) {
		this.valorobtenido = valorobtenido;
	}

	public List<ProgramaDTO> getListaprincipales() {
		return listaprincipales;
	}

	public void setListaprincipales(List<ProgramaDTO> listaprincipales) {
		this.listaprincipales = listaprincipales;
	}

	public Integer getIdprograma() {
		return idprograma;
	}

	public void setIdprograma(Integer idprograma) {
		this.idprograma = idprograma;
	}

	public ProgramaDTO getBloqueDTO() {
		return bloqueDTO;
	}

	public void setBloqueDTO(ProgramaDTO bloqueDTO) {
		this.bloqueDTO = bloqueDTO;
	}

	public ProgramaDTO getBloSeleccionada() {
		return bloSeleccionada;
	}

	public void setBloSeleccionada(ProgramaDTO bloSeleccionada) {
		this.bloSeleccionada = bloSeleccionada;
	}

	public List<ProgramaDTO> getListabloque() {
		return listabloque;
	}

	public void setListabloque(List<ProgramaDTO> listabloque) {
		this.listabloque = listabloque;
	}

	public List<ProgramaDTO> getListabloque2() {
		return listabloque2;
	}

	public void setListabloque2(List<ProgramaDTO> listabloque2) {
		this.listabloque2 = listabloque2;
	}

	public List<ProgramaDTO> getListabloque3() {
		return listabloque3;
	}

	public void setListabloque3(List<ProgramaDTO> listabloque3) {
		this.listabloque3 = listabloque3;
	}

	public ProgramaDTO getUbicacionesDTO() {
		return ubicacionesDTO;
	}

	public void setUbicacionesDTO(ProgramaDTO ubicacionesDTO) {
		this.ubicacionesDTO = ubicacionesDTO;
	}

	public ProgramaDTO getLicSeleccionada() {
		return licSeleccionada;
	}

	public void setLicSeleccionada(ProgramaDTO licSeleccionada) {
		this.licSeleccionada = licSeleccionada;
	}

	public List<ProgramaDTO> getListaunidad() {
		return listaunidad;
	}

	public void setListaunidad(List<ProgramaDTO> listaunidad) {
		this.listaunidad = listaunidad;
	}

	public ProgramaDTO getPropositosDTO() {
		return propositosDTO;
	}

	public void setPropositosDTO(ProgramaDTO propositosDTO) {
		this.propositosDTO = propositosDTO;
	}

	public ProgramaDTO getPropositosSeleccionada() {
		return propositosSeleccionada;
	}

	public void setPropositosSeleccionada(ProgramaDTO propositosSeleccionada) {
		this.propositosSeleccionada = propositosSeleccionada;
	}

	public List<ProgramaDTO> getListaproposito() {
		return listaproposito;
	}

	public void setListaproposito(List<ProgramaDTO> listaproposito) {
		this.listaproposito = listaproposito;
	}

	public ProgramaDTO getCriteriosDTO() {
		return criteriosDTO;
	}

	public void setCriteriosDTO(ProgramaDTO criteriosDTO) {
		this.criteriosDTO = criteriosDTO;
	}

	public ProgramaDTO getCriteriosSeleccionada() {
		return criteriosSeleccionada;
	}

	public void setCriteriosSeleccionada(ProgramaDTO criteriosSeleccionada) {
		this.criteriosSeleccionada = criteriosSeleccionada;
	}

	public List<ProgramaDTO> getListacriterios() {
		return listacriterios;
	}

	public void setListacriterios(List<ProgramaDTO> listacriterios) {
		this.listacriterios = listacriterios;
	}

	public ProgramaDTO getBibliografiasDTO() {
		return bibliografiasDTO;
	}

	public void setBibliografiasDTO(ProgramaDTO bibliografiasDTO) {
		this.bibliografiasDTO = bibliografiasDTO;
	}

	public ProgramaDTO getBibliografiasSeleccionada() {
		return bibliografiasSeleccionada;
	}

	public void setBibliografiasSeleccionada(ProgramaDTO bibliografiasSeleccionada) {
		this.bibliografiasSeleccionada = bibliografiasSeleccionada;
	}

	public List<ProgramaDTO> getListabibliografias() {
		return listabibliografias;
	}

	public void setListabibliografias(List<ProgramaDTO> listabibliografias) {
		this.listabibliografias = listabibliografias;
	}





}
