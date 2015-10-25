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
import com.eightdevelopers.sicva.dto.MiembroDTO;

/**
 * 
 * @author David Candia
 *
 */
public class S_MiembroDAO {

	// LISTAR LICENCIATURAS
	public List<MiembroDTO> listarasistenciacursos() {
		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT m.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
							+ "CASE WHEN m.evidencia > '' THEN 'SI' ELSE 'NO' END existencia "
							+ "FROM miembros_de_organizaciones m "
							+ "INNER JOIN usuarios u ON u.id_usuarios = m.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um ON um.id_usuarios = m.id_usuario_modificacion "
							+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol  "
							+ "WHERE m.id_lic_inscrita = ? ORDER BY u.apellido_paterno");

			List<MiembroDTO> listado = new ArrayList<MiembroDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				MiembroDTO miembroDTO = new MiembroDTO();

				miembroDTO.setId(rs.getInt("id_miembro"));
				miembroDTO.setPeriodo(rs.getString("periodo"));
				miembroDTO.setOrganizacion(rs.getString("organizacion"));
				miembroDTO.setNombramiento(rs.getString("nombramiento"));
				miembroDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				miembroDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				miembroDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				miembroDTO.setNombre(rs.getString("nombre"));
				miembroDTO.setNombreodif(rs.getString("nombre_modi"));
				miembroDTO.setRol(rs.getString("descripcion"));
				miembroDTO.setExistencia(rs.getString("existencia"));

				listado.add(miembroDTO);
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
	public String EliminarMiembro(MiembroDTO miembroDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_miembro(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, miembroDTO.getId());
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
	public String ActualizarLicenciatura(MiembroDTO miembroDTO, int IdUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_miembro(?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, miembroDTO.getId());
				callableStatement.setString(3, miembroDTO.getPeriodo());
				callableStatement.setString(4, miembroDTO.getOrganizacion());
				callableStatement.setString(5, miembroDTO.getNombramiento());
				callableStatement.setInt(6, IdUsuMod);
				callableStatement.setInt(7, miembroDTO.getIdlic());
				callableStatement.setInt(8, miembroDTO.getIdusuario());

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

	public String ActualizarMiembro(MiembroDTO miembroDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_miembro(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, miembroDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, miembroDTO.getEvidencia().getInputstream());
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
