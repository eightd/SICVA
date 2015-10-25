package com.eightdevelopers.sicva.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.db.SQLQueries;
import com.eightdevelopers.sicva.dto.ContraseniaDTO;

public class ContraseniaDAO {

	public boolean verifica(ContraseniaDTO objeto, String id) {

		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;
			ResultSet rs = null;
			String query = "";
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);
			query = "select  id_usuarios,contrasenia from usuarios where  id_usuarios='" + id + "'  and contrasenia='"
					+ objeto.getPass() + "' ";

			rs = sqlQueries.consulta(query);

			if (rs.next()) {
				conexionBD.cerrar();
				return true;
			}

			else {
				conexionBD.cerrar();
				return false;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());

			return false;
		}
	}

	public boolean update(ContraseniaDTO objetoDTO, String id) {
		System.out.println("metodo update " + objetoDTO.getPassNuevo());
		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;
			String query = "";
			conexionBD.abrir();

			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);

			query = " UPDATE usuarios SET contrasenia='" + objetoDTO.getPassNuevo()
					+ "' , contrasenia_estado=1  where id_usuarios='" + id + "'";
			sqlQueries.actualiza(query);

			return true;

		} catch (Exception ex) {
			System.out.println("Update " + ex.getMessage());

			return false;
		}
	}

}
