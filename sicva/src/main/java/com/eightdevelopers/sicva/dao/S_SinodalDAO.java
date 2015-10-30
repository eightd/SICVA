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
import com.eightdevelopers.sicva.dto.SinodalDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Sinodal' de la Base de Datos
 * 'SICVA'
 * 
 * @author David Candia
 *
 */
public class S_SinodalDAO {
	// LISTAR
	public List<SinodalDTO> ListarSinodales() {
		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT s.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
							+ "CASE WHEN s.evidencia > '' THEN 'SI' ELSE 'NO' END existencia " + "FROM sinodales s "
							+ "INNER JOIN usuarios u ON u.id_usuarios = s.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um ON um.id_usuarios = s.id_usuario_modificacion "
							+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol  "
							+ "WHERE s.id_lic_inscrita = ? ORDER BY u.apellido_paterno");

			List<SinodalDTO> listado = new ArrayList<SinodalDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				SinodalDTO sinodalDTO = new SinodalDTO();

				sinodalDTO.setId(rs.getInt("id_sinodal"));
				sinodalDTO.setProyecto(rs.getString("proyecto"));
				sinodalDTO.setFechatitulacion(rs.getString("fecha_de_titulacion"));
				sinodalDTO.setAlumno(rs.getString("alumnos"));
				sinodalDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				sinodalDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				sinodalDTO.setIdopc(rs.getInt("opciones_de_titulacion_id_opciones_de_titulacion"));
				sinodalDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				sinodalDTO.setFechamodif(rs.getString("fecha_modificacion"));
				sinodalDTO.setNivel(rs.getInt("niveles_educativos_id_nivel_educativo"));
				sinodalDTO.setNombre(rs.getString("nombre"));
				sinodalDTO.setNombreodif(rs.getString("nombre_modi"));
				sinodalDTO.setRol(rs.getString("descripcion"));
				sinodalDTO.setExistencia(rs.getString("existencia"));

				listado.add(sinodalDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarsinodales()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarSinodal(SinodalDTO sinodalDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_sinodal(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, sinodalDTO.getId());
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

	// ACTUALIZAR
	public String ActualizarSinodal(SinodalDTO sinodalDTO, int IdUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_sinodal(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, sinodalDTO.getId());
				callableStatement.setString(3, sinodalDTO.getProyecto());
				callableStatement.setString(4, sinodalDTO.getFechatitulacion());
				callableStatement.setString(5, sinodalDTO.getAlumno());
				callableStatement.setInt(6, IdUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(7, Fecha);
				callableStatement.setInt(8, sinodalDTO.getIdlic());
				callableStatement.setInt(9, sinodalDTO.getIdopc());
				callableStatement.setInt(10, sinodalDTO.getIdusuario());
				callableStatement.setInt(11, sinodalDTO.getNivel());

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

	// ACTUALIZAR EVIDENCIA
	public String ActualizarEvidencia(SinodalDTO sinodalDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_sinodal(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, sinodalDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(4, Fecha);
				callableStatement.setBinaryStream(5, sinodalDTO.getEvidencia().getInputstream());
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
