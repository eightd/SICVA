package com.eightdevelopers.sicva.db;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLQueries{

	
	Connection conexion = null;
	
	

	public SQLQueries(Connection conexion){
		this.conexion=conexion;		
	}
	
	/**
     * Metodo para  consultar base de datos 
     * @param consulta  cadena reoeresentativa de una consulta 
     * @return   retorna  un objeto ResultSet que retorna un conjunto de resultados  que despues vamos a iterar para mostrarlos en pantalla
     */
    public ResultSet consulta(String consulta) {
        try {
            Statement st = this.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            return rs;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return null;
        } 
    }

    /**
     * Metodo para  modificar una  base de datos 
     * @param consulta  cadena representativa de una consulta 
     * @return   true  si la ejecucion fue exitosa caso de los contrario fasle  
     */
    public boolean actualiza(String consulta) {
        try {
            Statement st = this.getConexion().createStatement();
            return st.execute(consulta);
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            return false;
        } 
    }
    
    public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
}
