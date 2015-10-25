package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.LicenciaturaDTO;
import com.eightdevelopers.sicva.dto.RolesDTO;
import com.eightdevelopers.sicva.dto.UsuariosDTO;

/**
 * Clase que permite hacer el CRUD de un 'coordinador y secretaria' en la tabla
 * 'Usuarios' de la Base de Datos SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */
public class UsuariosDAO {

	/**
	 * Método que permite insertar un nuevo registro a la tabla 'usuarios' de la
	 * BD SICVA
	 * 
	 * @param datos
	 *            de tipo usuariosDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String GuardarUsuario(UsuariosDTO usuariosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_usuario(?,?,?,?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, usuariosDTO.getNombre());
				callableStatement.setString(3, usuariosDTO.getAp());
				callableStatement.setString(4, usuariosDTO.getAm());
				callableStatement.setString(5, usuariosDTO.getUsuario());
				callableStatement.setString(6, usuariosDTO.getContra());
				callableStatement.setInt(7, usuariosDTO.getIdlic());
				callableStatement.setInt(8, usuariosDTO.getIdrol());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "¡Se registro satisfactoriamente!";

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

	/**
	 * Método que permite realizar una consulta a la tabla 'usuarios' y obtener
	 * un listado de los datos registrados
	 * 
	 * @return arreglo de tipo UsuariosDTO
	 */
	public List<UsuariosDTO> listarUsuarios() {
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT usuarios.id_usuarios,usuarios.nombre,usuarios.apellido_paterno,"
							+ "usuarios.apellido_materno,usuarios.usuario,usuarios.contrasenia,"
							+ "usuarios.contrasenia_estado,usuarios.id_lic,licenciaturas.descripcion,"
							+ "usuarios.roles_id_rol,roles.descripcion,usuarios.estado FROM usuarios"
							+ " INNER JOIN licenciaturas ON usuarios.id_lic = licenciaturas.id_licenciatura"
							+ " INNER JOIN roles ON usuarios.roles_id_rol = roles.id_rol "
							+ " WHERE usuarios.estado=1 AND usuarios.roles_id_rol!=1 AND usuarios.roles_id_rol !=4"
							+ " ORDER BY roles.descripcion, licenciaturas.descripcion, usuarios.nombre");
			List<UsuariosDTO> listadoUsu = new ArrayList<UsuariosDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;
			while (rs.next()) {
				UsuariosDTO usuariosDTO = new UsuariosDTO();
				usuariosDTO.setId(rs.getInt("id_usuarios"));
				usuariosDTO.setNombre(rs.getString("nombre"));
				usuariosDTO.setAp(rs.getString("apellido_paterno"));
				usuariosDTO.setAm(rs.getString("apellido_materno"));
				usuariosDTO.setUsuario(rs.getString("usuario"));
				usuariosDTO.setContra(rs.getString("contrasenia"));
				usuariosDTO.setContra_estado(rs.getInt("contrasenia_estado"));
				usuariosDTO.setIdlic(rs.getInt("id_lic"));
				usuariosDTO.setDes_lic(rs.getString("descripcion"));
				usuariosDTO.setIdrol(rs.getInt("roles_id_rol"));
				usuariosDTO.setDesc_rol(rs.getString("roles.descripcion"));
				listadoUsu.add(usuariosDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listadoUsu;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarUsuarios()" + e.getMessage());
			return null;
		}

	}

	/***
	 * Método que permite actualizar los registros de la tabla 'usuarios' de la
	 * BD SICVA
	 * 
	 * @param datos
	 *            de tipo usuariosDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String ActualizarUsu(UsuariosDTO usuariosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_usuario(?,?,?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, usuariosDTO.getId());
				callableStatement.setString(3, usuariosDTO.getNombre());
				callableStatement.setString(4, usuariosDTO.getAp());
				callableStatement.setString(5, usuariosDTO.getAm());
				callableStatement.setInt(6, usuariosDTO.getIdlic());
				callableStatement.setInt(7, usuariosDTO.getIdrol());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "¡Se actualizo satisfactoriamente!";
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

	/**
	 * Método que permite eliminar un registro de la tabla 'usuarios' de la BD
	 * SICVA
	 * 
	 * @param datos
	 *            de tipo usuariosDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String EliminarUsu(UsuariosDTO usuariosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_usuario(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, usuariosDTO.getId());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "¡Se elimino satisfactoriamente!";
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

	/**
	 * Método para resetear la contraseña de una secretaria y coordinador
	 * 
	 * @param datos
	 *            de tipo usuariosDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String ResetContra(UsuariosDTO usuariosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call reset_contrasena(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, usuariosDTO.getId());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "¡Se reseteo contraseña satisfactoriamente!";
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

	/**
	 * Método que permite realizar una consulta a la tabla 'licenciaturas' y
	 * obtener un listado de los datos registrados
	 * 
	 * @return arreglo de tipo LicenciaturaDTO
	 */
	public List<LicenciaturaDTO> listarlicenciatura() {
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM licenciaturas WHERE estado=1 ORDER BY descripcion");
			List<LicenciaturaDTO> listado = new ArrayList<LicenciaturaDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;
			while (rs.next()) {
				LicenciaturaDTO licenciaturaDTO = new LicenciaturaDTO();
				licenciaturaDTO.setId(rs.getInt("id_licenciatura"));
				licenciaturaDTO.setDescripcion(rs.getString("descripcion"));
				listado.add(licenciaturaDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarlicenciatura()" + e.getMessage());
			return null;
		}

	}

	/**
	 * Método que permite realizar una consulta a la tabla 'usuarios' y obtener
	 * un listado de los datos registrados
	 * 
	 * @return arreglo de tipo RolesDTO
	 */
	public List<RolesDTO> listarRoles() {
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT *FROM roles  where id_rol !=1 AND id_rol !=4");
			List<RolesDTO> listadorol = new ArrayList<RolesDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;
			while (rs.next()) {
				RolesDTO rolesDTO = new RolesDTO();
				rolesDTO.setId(rs.getInt("id_rol"));
				rolesDTO.setDescripcion(rs.getString("descripcion"));
				listadorol.add(rolesDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listadorol;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarlicenciatura()" + e.getMessage());
			return null;
		}

	}
}
