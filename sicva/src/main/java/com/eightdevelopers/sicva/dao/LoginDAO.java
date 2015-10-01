package com.eightdevelopers.sicva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.eightdevelopers.sicva.db.ConexionBD;

import com.eightdevelopers.sicva.dto.UsuarioDTO;
/**
 * esta clase  realiza la conexion repectiva del inicio de sesion con la base de datos
 * @author Leonardo Zavala Torres
 * @version 1.0
 * 
 */
public class LoginDAO {
	public static boolean validateSuper(String user, String password) {
		Connection con = null;

		try {
			PreparedStatement ps = null;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			con = conexionBD.getConexion();
			ps = con.prepareStatement(
					"Select nombre_usuario, contrasenia,roles_id_rol from usuarios where nombre_usuario = ? and contrasenia = ? and roles_id_rol=1");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return true;

			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			// ConexionBD.cerrar();
		}
		return false;
	}

	public List<UsuarioDTO> isAcountExists(String user, String pass) {
		try {
			ConexionBD conexionBD = new ConexionBD();
			PreparedStatement ps = null;
			List<UsuarioDTO> listUsuarioEnSessionDTO = new ArrayList<UsuarioDTO>();
			UsuarioDTO filaUsuarioDTO = null;
			ResultSet rs = null;
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			ps = conn.prepareStatement(
					"Select id_usuarios,usuario,contrasenia_estado, contrasenia,id_lic,roles_id_rol from usuarios where usuario = ? and contrasenia = ?");
			ps.setString(1, user);
			System.out.println(user);
			ps.setString(2, pass);
			System.out.println(pass);
			rs = ps.executeQuery();

			int count = 0;
			while (rs.next()) {
				filaUsuarioDTO = new UsuarioDTO();
				filaUsuarioDTO.setId(rs.getInt("id_usuarios"));

				filaUsuarioDTO.setUsuario(rs.getString("usuario"));

				filaUsuarioDTO.setContra_estado(rs.getInt("contrasenia_estado"));

				filaUsuarioDTO.setPass(rs.getString("contrasenia"));
				
				filaUsuarioDTO.setId_lic(rs.getInt("id_lic"));

				filaUsuarioDTO.setRol(rs.getInt("roles_id_rol"));
				listUsuarioEnSessionDTO.add(filaUsuarioDTO);

				count++;

				return listUsuarioEnSessionDTO;
			}
			if (count == 0) {
				System.out.println("Usuario no registrado");
			}
			conexionBD.cerrar();
		} catch (Exception ex) {
			System.out.println("UsuarioDAO.isAcountExists:" + ex.getMessage());
			return null;
		}
		return null;
	}

	
	

	

}
