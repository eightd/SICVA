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
import com.eightdevelopers.sicva.dto.ComisionesDTO;

public class S_ComisionesDAO {

	// LISTAR
	public List<ComisionesDTO> listarComisiones() {
		System.out.println("Entró a listar comisiones..");
		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT c.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
							+ "CASE WHEN c.evidencia > '' THEN 'SI' ELSE 'NO' END existencia FROM comisiones c "
							+ "INNER JOIN usuarios u ON u.id_usuarios = c.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um ON um.id_usuarios = c.id_usuario_modificacion "
							+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol "
							+ "WHERE c.id_lic_inscrita = ? ORDER BY u.apellido_paterno");
			List<ComisionesDTO> listado = new ArrayList<ComisionesDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				ComisionesDTO comisionesDTO = new ComisionesDTO();

				comisionesDTO.setId(rs.getInt("id_comision"));
				comisionesDTO.setFecha(rs.getString("fecha"));
				comisionesDTO.setEvento(rs.getString("evento"));
				comisionesDTO.setInstitucion(rs.getString("institucion"));
				comisionesDTO.setLugar(rs.getString("lugar"));
				comisionesDTO.setParticipacion(rs.getString("participacion"));
				comisionesDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				comisionesDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				comisionesDTO.setName(rs.getString("nombre"));
				comisionesDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				comisionesDTO.setFechamodif(rs.getString("fecha_modificacion"));
				comisionesDTO.setNombreodif(rs.getString("nombre_modi"));
				comisionesDTO.setRol(rs.getString("descripcion"));
				comisionesDTO.setExistencia(rs.getString("existencia"));

				listado.add(comisionesDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listado;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error en listarComisiones()" + e.getMessage());
			return null;
		}
	}

	// ELIMINAR
	public String EliminarComision(ComisionesDTO comisionesDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_comisiones(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, comisionesDTO.getId());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se elimino satisfactoriamente";
				} else {
					return "";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en EliminarComision()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR

	public String ActualizarComision(ComisionesDTO comisionesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_comisiones(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, comisionesDTO.getId());
				callableStatement.setString(3, comisionesDTO.getFecha());
				callableStatement.setString(4, comisionesDTO.getEvento());
				callableStatement.setString(5, comisionesDTO.getInstitucion());
				callableStatement.setString(6, comisionesDTO.getLugar());
				callableStatement.setString(7, comisionesDTO.getParticipacion());
				callableStatement.setInt(8, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(9, Fecha);
				callableStatement.setInt(10, comisionesDTO.getIdlic());
				callableStatement.setInt(11, comisionesDTO.getIdusuario());

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
			System.out.println("Error en ActualizarComision()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR EVIDENCIA

	public String ActualizarEvidencia(ComisionesDTO comisionesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_comisiones(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, comisionesDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(4, Fecha);
				callableStatement.setBinaryStream(5, comisionesDTO.getEvidencia().getInputstream());
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
