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
import com.eightdevelopers.sicva.dto.DocentesDTO;


/**
 * Clase que permite hacer el CRUD de un 'Docente' en la tabla 'Usuarios' de la
 * Base de Datos SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */
public class DocentesDAO {
	/**
	 * Método que permite registrar un docente a la tabla usuarios
	 * 
	 * @param docentesDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String GuardarDocente(DocentesDTO docentesDTO) {

		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_docente(?,?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, docentesDTO.getNombre());
				callableStatement.setString(3, docentesDTO.getAp());
				callableStatement.setString(4, docentesDTO.getAm());
				callableStatement.setString(5, docentesDTO.getUsuario());
				callableStatement.setString(6, docentesDTO.getContra());
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
	 * un listado de los docentes registrados
	 * 
	 * @return arreglo de tipo DocentesDTO
	 */
	public List<DocentesDTO> listarDocentes() {
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
							+ " WHERE usuarios.estado=1 AND usuarios.roles_id_rol=4"
							+ " ORDER BY roles.descripcion,usuarios.nombre");
			List<DocentesDTO> listadoDocentes = new ArrayList<DocentesDTO>();
			ResultSet rs = ps.executeQuery();
			boolean found = false;
			while (rs.next()) {
				DocentesDTO docentesDTO = new DocentesDTO();
				docentesDTO.setId(rs.getInt("id_usuarios"));
				docentesDTO.setNombre(rs.getString("nombre"));
				docentesDTO.setAp(rs.getString("apellido_paterno"));
				docentesDTO.setAm(rs.getString("apellido_materno"));
				docentesDTO.setUsuario(rs.getString("usuario"));
				docentesDTO.setContra(rs.getString("contrasenia"));
				docentesDTO.setContra_estado(rs.getInt("contrasenia_estado"));
				docentesDTO.setIdrol(rs.getInt("roles_id_rol"));
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
	/**
	 * Método que permite realizar una consulta a la tabla 'usuarios' y obtener
	 * un listado de los docentes registrados
	 * 
	 * @return arreglo de tipo DocentesDTO
	 */
	public List<DocentesDTO> listarDocentesLic() {
		try {
			String valor = obtenerValorSesion("lic");
			String idUser = obtenerValorSesion("id");
			String idRol = obtenerValorSesion("rol");
	
			int idLic_session= Integer.parseInt(valor);
			int idUser_session = Integer.parseInt(idUser);
			int idRol_session = Integer.parseInt(idRol);
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps=null;
			 List<DocentesDTO> listadoDocentes = new ArrayList<DocentesDTO>();
			if(idRol_session!=4){
			
			 ps = connection
					.prepareStatement("select usuarios.nombre,usuarios.apellido_paterno,apellido_materno,vinculaciones.usuarios_id_usuarios,licenciaturas.descripcion from usuarios inner join"
							+ " (vinculaciones inner join licenciaturas on vinculaciones.licenciaturas_id_licenciatura = licenciaturas.id_licenciatura)"
							+ " on usuarios.id_usuarios = vinculaciones.usuarios_id_usuarios where vinculaciones.licenciaturas_id_licenciatura=?");
			
				
				ps.setInt(1, idLic_session);
			}
			if(idRol_session==4){
				
				 ps = connection
						.prepareStatement("select usuarios.nombre,usuarios.apellido_paterno,apellido_materno,vinculaciones.usuarios_id_usuarios,licenciaturas.descripcion from usuarios inner join"
+"(vinculaciones inner join licenciaturas on vinculaciones.licenciaturas_id_licenciatura = licenciaturas.id_licenciatura)"+
"on usuarios.id_usuarios = vinculaciones.usuarios_id_usuarios where vinculaciones.usuarios_id_usuarios=?");
				
					ps.setInt(1, idUser_session);	
			}
			
			ResultSet rs = ps.executeQuery();
			boolean found = false;
			while (rs.next()) { 
				DocentesDTO docentesDTO = new DocentesDTO();
				docentesDTO.setId(rs.getInt("vinculaciones.usuarios_id_usuarios"));
				docentesDTO.setNombre(rs.getString("usuarios.nombre"));
				docentesDTO.setAp(rs.getString("usuarios.apellido_paterno"));
				docentesDTO.setAm(rs.getString("usuarios.apellido_materno"));

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
			System.out.println("Error en listarDocentesLic()" + e.getMessage());
			return null;
		}

	}
	/**
	 * Método qie permite actualizar la informacion de un docente
	 * 
	 * @param datos
	 *            de tipo docentesDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String ActualizarDocen(DocentesDTO docentesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_docente(?,?,?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, docentesDTO.getId());
				callableStatement.setString(3, docentesDTO.getNombre());
				callableStatement.setString(4, docentesDTO.getAp());
				callableStatement.setString(5, docentesDTO.getAm());
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
	 * Método que permite eliminar un docente de la tabla 'usuarios' de la BD
	 * SICVA
	 * 
	 * @param datos
	 *            de tipo DocentesDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String EliminarDocen(DocentesDTO docentesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_docente(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, docentesDTO.getId());
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
	 * Método para resetear la contraseña de un docente 
	 * 
	 * @param datos
	 *            de tipo docentesDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String ResetContra(DocentesDTO docentesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call reset_contraDocen(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, docentesDTO.getId());
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
	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}
}
