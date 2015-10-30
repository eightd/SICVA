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
import com.eightdevelopers.sicva.dto.TesisDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de Datos
 * 'SICVA'
 * 
 * @author David Candia
 *
 */
public class S_TesisDAO {
	// LISTAR
	public List<TesisDTO> listarAsosoriasTesis() {

		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT a.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
							+ "CASE WHEN a.evidencia > '' THEN 'SI' ELSE 'NO' END existencia "
							+ "FROM asesorias_de_tesis a "
							+ "INNER JOIN usuarios u ON u.id_usuarios = a.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um ON um.id_usuarios = a.id_usuario_modificacion "
							+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol  "
							+ "WHERE a.id_lic_inscrita = ? ORDER BY u.apellido_paterno");
			List<TesisDTO> listado = new ArrayList<TesisDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				TesisDTO tesisDTO = new TesisDTO();

				tesisDTO.setId(rs.getInt("id_asesorias_tesis"));
				tesisDTO.setProyecto(rs.getString("proyecto"));
				tesisDTO.setFechatitulacion(rs.getString("fecha_de_titulacion"));
				tesisDTO.setAlumno(rs.getString("alumno"));
				tesisDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				tesisDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				tesisDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				tesisDTO.setFechamodif(rs.getString("fecha_modificacion"));
				tesisDTO.setId_nivel(rs.getInt("niveles_educativos_id_nivel_educativo"));
				tesisDTO.setNombre(rs.getString("nombre"));
				tesisDTO.setNombreodif(rs.getString("nombre_modi"));
				tesisDTO.setRol(rs.getString("descripcion"));
				tesisDTO.setExistencia(rs.getString("existencia"));

				listado.add(tesisDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarAsosoriasTesis()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarAsesoriaTesis(TesisDTO tesisDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_tesis(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, tesisDTO.getId());
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
			System.out.println("Error en EliminarAsesoriaTesis()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR

	public String ActualizarAsesoriasTesis(TesisDTO tesisDTO, int IdUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_tesis(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, tesisDTO.getId());
				callableStatement.setString(3, tesisDTO.getProyecto());
				callableStatement.setString(4, tesisDTO.getFechatitulacion());
				callableStatement.setString(5, tesisDTO.getAlumno());
				callableStatement.setInt(6, IdUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(7, Fecha);
				callableStatement.setInt(8, tesisDTO.getIdlic());
				callableStatement.setInt(9, tesisDTO.getIdusuario());
				callableStatement.setInt(10, tesisDTO.getId_nivel());

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
			System.out.println("Error en ActualizarAsesoriasTesis()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR EVIDENCIA

	public String ActualizarEvidencia(TesisDTO tesisDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_tesis(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, tesisDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(4, Fecha);
				callableStatement.setBinaryStream(5, tesisDTO.getEvidencia().getInputstream());
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
