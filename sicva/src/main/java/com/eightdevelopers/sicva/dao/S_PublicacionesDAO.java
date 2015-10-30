package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.PublicacionesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Publicaciones' de la Base de
 * Datos 'SICVA'
 * 
 * @author David Candia
 *
 */

// GUARDAR

public class S_PublicacionesDAO {

	// LISTAR
	public List<PublicacionesDTO> listarPublicaciones() {

		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT p.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
					+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
					+ "CASE WHEN p.evidencia > '' THEN 'SI' ELSE 'NO' END existencia "
					+ "FROM publicaciones p "
					+ "INNER JOIN usuarios u ON u.id_usuarios = p.usuarios_id_usuarios "
					+ "INNER JOIN usuarios um ON um.id_usuarios = p.id_usuario_modificacion "
					+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol  "
					+ "WHERE p.id_lic_inscrita = ? ORDER BY u.apellido_paterno");
			List<PublicacionesDTO> listado = new ArrayList<PublicacionesDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				PublicacionesDTO publicacionesDTO = new PublicacionesDTO();

				publicacionesDTO.setId(rs.getInt("id_publicacion"));
				publicacionesDTO.setPeriodo(rs.getString("periodo"));
				publicacionesDTO.setPublicacion(rs.getString("publicacion"));
				publicacionesDTO.setInstitucion(rs.getString("institucion"));
				publicacionesDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				publicacionesDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				publicacionesDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				publicacionesDTO.setFechamodif(rs.getString("fecha_modificacion"));
				publicacionesDTO.setNombre(rs.getString("nombre"));
				publicacionesDTO.setNombreodif(rs.getString("nombre_modi"));
				publicacionesDTO.setRol(rs.getString("descripcion"));
				publicacionesDTO.setExistencia(rs.getString("existencia"));

				listado.add(publicacionesDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarcomisiones()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarPublicacion(PublicacionesDTO publicacionesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_publicaciones(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, publicacionesDTO.getId());
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
			System.out.println("Error en EliminarPublicacion()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR
	public String ActualizarPublicacion(PublicacionesDTO publicacionesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_publicaciones(?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, publicacionesDTO.getId());
				callableStatement.setString(3, publicacionesDTO.getPeriodo());
				callableStatement.setString(4, publicacionesDTO.getPublicacion());
				callableStatement.setString(5, publicacionesDTO.getInstitucion());
				callableStatement.setInt(6, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(7, Fecha);
				callableStatement.setInt(8, publicacionesDTO.getIdlic());
				callableStatement.setInt(9, publicacionesDTO.getIdusuario());

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
			System.out.println("Error en ActualizarPublicacion()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR EVIDENCIA
	public String ActualizarEvidencia(PublicacionesDTO publicacionesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_publicaciones(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, publicacionesDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(4, Fecha);
				callableStatement.setBinaryStream(5, publicacionesDTO.getEvidencia().getInputstream());
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
			System.out.println("Error en ActualizarEvidencia()..." + ex.getMessage());
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