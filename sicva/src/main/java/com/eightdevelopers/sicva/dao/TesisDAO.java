package com.eightdevelopers.sicva.dao;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.StreamedContent;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.TesisDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

// GUARDAR

public class TesisDAO {
	public String guardarDato(TesisDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_tesis(?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getProyecto());
				callableStatement.setString(3, asistenciacursosDTO.getFechatitulacion());
				callableStatement.setString(4, asistenciacursosDTO.getAlumno());
				callableStatement.setBinaryStream(5, (InputStream) asistenciacursosDTO.getEvidencia());
				callableStatement.setInt(6, 1);
				callableStatement.setInt(7, 1);
				callableStatement.setInt(8, 1);
				callableStatement.setInt(9, asistenciacursosDTO.getNivel());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "Se ha guardado satisfactoriamente";

				} else {
					return "Error al guardar, asesoría de tesis existente";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "Se ha guardado satisfactoriamente";

	}

	// LISTAR LICENCIATURAS
	/**
	 * 
	 * @return
	 */
	public List<TesisDTO> listarasistenciacursos() {

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM asesorias_de_tesis");
			List<TesisDTO> listado = new ArrayList<TesisDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				TesisDTO asistenciacursosDTO = new TesisDTO();

				asistenciacursosDTO.setId(rs.getInt("id_asesorias_tesis"));
				asistenciacursosDTO.setProyecto(rs.getString("proyecto"));
				asistenciacursosDTO.setFechatitulacion(rs.getString("fecha_de_titulacion"));
				asistenciacursosDTO.setAlumno(rs.getString("alumno"));
				asistenciacursosDTO.setEvidencia((StreamedContent) rs.getBinaryStream("evidencia"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setNivel(rs.getInt("niveles_educativos_id_nivel_educativo"));

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
			System.out.println("Error en listarcomisiones()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarAsistenciaCurso(TesisDTO escolaridaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_tesis(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridaDTO.getId());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se ha eliminado satisfactoriamente";
				} else {
					return "No se pudo eliminar";

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

	public String ActualizarLicenciatura(TesisDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_tesis(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getProyecto());
				callableStatement.setString(4, asistenciacursosDTO.getFechatitulacion());
				callableStatement.setString(5, asistenciacursosDTO.getAlumno());
				callableStatement.setBinaryStream(6, (InputStream) asistenciacursosDTO.getEvidencia());
				callableStatement.setInt(7, 1);
				callableStatement.setInt(8, 1);
				callableStatement.setInt(9, 1);
				callableStatement.setInt(10, asistenciacursosDTO.getNivel());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "Se ha Actualizo satisfactoriamente";
				} else {
					return "No se pudo Actualizar por duplicado";

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			System.out.println("Error en.." + ex.getMessage());
		}

		return "";

	}
}
