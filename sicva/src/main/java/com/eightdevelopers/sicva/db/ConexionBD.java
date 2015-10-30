package com.eightdevelopers.sicva.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {
	private final static String DB_USER = "root";
    private final static String DB_PASSWORD = "123456";
    private final static String DB_NAME = "sicva";
    //private final static String DB_SRT_CON = "jdbc:mysql:///sicva?useUnicode=true&characterEncoding=utf-8";
    private final static String DB_SRT_CON = "jdbc:mysql://localhost:3306/" + DB_NAME;
    Connection conexion = null;

	/**
     * Metodo que realiza una conexion  a base de datos
     * @return  una conexion a base de datos 
     */
    public void abrir() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.setConexion( DriverManager.getConnection(DB_SRT_CON,DB_USER,DB_PASSWORD));
            
            if(!conexion.isClosed()){
            	System.out.println("Se conectó a MySQL server...");
            }else{
            	System.out.println("No se conecto to MySQL server!...");
            }
        } catch (Exception ex) {
            System.out.println("Error:" + ex.getMessage());
            ex.getStackTrace();
        }
    }

    /**
     * Cierra conexion a la base de datos 
     */
    public void cerrar() {
        try {
            conexion.close();
            
        } catch (Exception ex) {
        	System.out.println("Se ha cerrado la conexión!...");
            System.out.println("Error:" + ex.getMessage());
        }
    }

    /**
     * Obtiene conexión activa
     * @return una conexión activa
     */
    public Connection getConexion() {
		return conexion;
	}

    /**
     * Coloca una conexión activa
     * @param conexion una conexión activa
     */
	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
   
}

