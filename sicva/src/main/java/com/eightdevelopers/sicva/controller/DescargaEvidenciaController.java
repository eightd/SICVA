package com.eightdevelopers.sicva.controller;

/**
 * Clase que permite descargar evidencias de la tablas en el campo evidencias
 * Datos 'SICVA'
 * 
 * @author Jose Inocencio Alvararo Rodriguez
 *
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.model.StreamedContent;
import com.eightdevelopers.sicva.dao.DescargaEvideciaDAO;


/**
 * @author HP-Jose
 *
 */
@ManagedBean(name="Descargar")
@SessionScoped

public class DescargaEvidenciaController {
 
	private StreamedContent file;
    private Integer id;
    private Integer num;
    
  
    public void Escolaridad(ActionEvent event){
    	
    	id = (Integer) event.getComponent().getAttributes().get("idEsc");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
    	
    	DescargaEvideciaDAO e = new DescargaEvideciaDAO();
    	file=e.Escolaridad(id,num);   	
    }  
   
    public void ExperienciaLaboral(ActionEvent event){
    	id = (Integer) event.getComponent().getAttributes().get("idExperiencia");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
    	file=e.ExperienciaLaboral(id,num);
    }
    
    public void Ponencias(ActionEvent event){
        id = (Integer) event.getComponent().getAttributes().get("idPonencias");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
    	file=e.Ponencias(id,num);
    	
    	System.out.println("archivo ponencias "+file);
   }
    
    
    public void AsistenciaCursos(ActionEvent event){
    	
        id = (Integer) event.getComponent().getAttributes().get("idCursos");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        System.out.println("Descarga de Asistencia a Cursos"+id);
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
    	file=e.Cursos(id,num);
    }
    
    public void Comisiones(ActionEvent event){
        id = (Integer) event.getComponent().getAttributes().get("idComisiones");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
    	file=e.Comisiones(id,num);
    }
    
    public void Publicaciones(ActionEvent event){
    	id = (Integer) event.getComponent().getAttributes().get("idPublicaciones");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
           
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
       	file=e.Publicaciones(id,num);
    }
    
    public void MiembroOrg(ActionEvent event){
    	id = (Integer) event.getComponent().getAttributes().get("idMiembro");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
       	file=e.MiembroOrg(id,num);
    }
    
    public void AsesoriaTesis(ActionEvent event){
        id = (Integer) event.getComponent().getAttributes().get("idTesis");
    	num = (Integer) event.getComponent().getAttributes().get("idNum");
        
        
        DescargaEvideciaDAO e = new DescargaEvideciaDAO();
       	file=e.AsesoriaTesis(id,num);
    }
    
    public void Sinodal(ActionEvent event){
    	 id = (Integer) event.getComponent().getAttributes().get("idSinodal");
    	 num = (Integer) event.getComponent().getAttributes().get("idNum");
    	    
          
         DescargaEvideciaDAO e = new DescargaEvideciaDAO();
         file=e.Sinodal(id,num);
    }

    
    

	
	
	

	/**
	 * @return
	 */
	public StreamedContent getFile() {
		return file;
	}


	public void setFile(StreamedContent file) {
		this.file = file;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}



	
	
}
