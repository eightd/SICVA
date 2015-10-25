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
import com.eightdevelopers.sicva.dto.AsistenciaCursosDTO;

public class S_AsistenciaCursosDAO {

	// LISTAR LICENCIATURAS
	/**
	 * 
	 * @return
	 */
	public List<AsistenciaCursosDTO> listarasistenciacursos() {
		try {
			String valor = obtenerValorSesion("lic");
			int lic_session;
			System.out.println("Entra a listar los cursos");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT a.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
					+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, "
					+ "r.descripcion, CASE WHEN a.evidencia > '' THEN 'SI' ELSE 'NO' END existencia FROM asistencias_cursos a "
					+ "INNER JOIN usuarios u ON u.id_usuarios = a.usuarios_id_usuarios "
					+ "INNER JOIN usuarios um ON um.id_usuarios = a.id_usuario_modificacion "
					+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol  "
					+ "WHERE a.id_lic_inscrita = ? ORDER BY u.apellido_paterno");
			List<AsistenciaCursosDTO> listado = new ArrayList<AsistenciaCursosDTO>();
			lic_session = Integer.parseInt(valor);
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				AsistenciaCursosDTO asistenciacursosDTO = new AsistenciaCursosDTO();

				asistenciacursosDTO.setId(rs.getInt("id_asistencia_curso"));
				asistenciacursosDTO.setPeriodo(rs.getString("periodo"));
				asistenciacursosDTO.setCurso(rs.getString("curso"));
				asistenciacursosDTO.setInstitucion(rs.getString("institucion"));
				asistenciacursosDTO.setLugar(rs.getString("lugar"));
				asistenciacursosDTO.setHoras(rs.getString("horas"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setName(rs.getString("nombre"));
				asistenciacursosDTO.setNombreodif(rs.getString("nombre_modi"));
				asistenciacursosDTO.setRol(rs.getString("descripcion"));
				asistenciacursosDTO.setExistencia(rs.getString("existencia"));

				listado.add(asistenciacursosDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarasistenciacursos()" + e.getMessage());
			return null;
		}

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

	// ELIMINAR
	public String EliminarAsistenciaCurso(AsistenciaCursosDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_asistenciascursos(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
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
			System.out.println("Error en EliminarAsistenciaCurso()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR

	public String ActualizarAsistencia(AsistenciaCursosDTO asistenciacursosDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_asistenciascursos(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(4, asistenciacursosDTO.getCurso());
				callableStatement.setString(5, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(6, asistenciacursosDTO.getLugar());
				callableStatement.setString(7, asistenciacursosDTO.getHoras());
				// Se cambia el modo de introduccir la imagen de (InputStream)
				// asistenciacursosDTO.getEvidencia()
				// a asistenciacursosDTO.getEvidencia().getInputstream()
				callableStatement.setInt(8, idUsuMod);
				callableStatement.setInt(9, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(10, asistenciacursosDTO.getIdusuario());

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
			System.out.println("Error en ActualizarAsistencia() ..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR EVIDENCIA

	public String ActualizarEvidencia(AsistenciaCursosDTO asistenciaCursosDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_asistenciascursos(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciaCursosDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, asistenciaCursosDTO.getEvidencia().getInputstream());
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

}
