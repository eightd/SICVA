package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.VinculacionDTO;

/**
 * Clase que permite hacer realizar la vinculacion en el modulo de secretaria' en la tabla
 * 'Usuarios' de la Base de Datos SICVA
 * 
 * @author 
 *
 */
public class VinculacionesDAO {
	
	
	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}
	
	
	public String VincularDocente(VinculacionDTO docDTO) {
				try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_vinculacion(?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				
				callableStatement.setInt(2,docDTO.getId_usuario() );
				callableStatement.setString(3,obtenerValorSesion("lic"));
				
				callableStatement.execute();
				
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "¡Se Vinculo satisfactoriamente!";

				} else {
					return "";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "";

	}
	
	
	
		public List<VinculacionDTO> listarDocentes() {
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT usuarios.id_usuarios,usuarios.nombre,usuarios.apellido_paterno,"
							+ "usuarios.apellido_materno,usuarios.usuario, usuarios.contrasenia,"
							+ "usuarios.contrasenia_estado,usuarios.roles_id_rol,roles.descripcion,usuarios.estado "
							+ " FROM usuarios "
							+ " INNER JOIN roles ON usuarios.roles_id_rol = roles.id_rol "
							+ " WHERE usuarios.estado=1 "
							+ " AND usuarios.roles_id_rol!=1 "
							+ " AND usuarios.roles_id_rol!=3 "
							+ " AND 1!=(select COUNT(*) FROM vinculaciones WHERE usuarios_id_usuarios=usuarios.id_usuarios "
							+ " AND licenciaturas_id_licenciatura="+obtenerValorSesion("lic")+")"
							+ " ORDER BY roles.descripcion,usuarios.nombre");
			
		
			          		
			               
			
			List<VinculacionDTO> listadoDocentes = new ArrayList<VinculacionDTO>();
			ResultSet rs = ps.executeQuery();
			boolean found = false;
			while (rs.next()) {
				VinculacionDTO docentesDTO = new VinculacionDTO();
				
				docentesDTO.setId_usuario(rs.getInt("id_usuarios"));
			    docentesDTO.setNombre(rs.getString("nombre"));
				docentesDTO.setAp(rs.getString("apellido_paterno"));
				docentesDTO.setAm(rs.getString("apellido_materno"));
				docentesDTO.setDesc_rol(rs.getString("descripcion"));
			    
				listadoDocentes.add(docentesDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listadoDocentes;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarDocentes()" + e.getMessage());
			return null;
		}

	}
	
	
	
	
}
