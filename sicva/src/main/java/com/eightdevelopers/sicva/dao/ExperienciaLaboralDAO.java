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
import com.eightdevelopers.sicva.dto.ExperienciaLaboralDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'experiencia_laboral' de la Base
 * de Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

// GUARDAR

public class ExperienciaLaboralDAO {
	public String guardarDato(ExperienciaLaboralDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_experiencialaboral(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(3, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(4, asistenciacursosDTO.getDpto());
				callableStatement.setString(5, asistenciacursosDTO.getLugar());
				callableStatement.setString(6, asistenciacursosDTO.getPuesto());
				callableStatement.setBinaryStream(7, (InputStream) asistenciacursosDTO.getEvidencia());
				callableStatement.setInt(8, 1);
				callableStatement.setInt(9, 1);
				callableStatement.setInt(10, 1);

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "Se ha guardado satisfactoriamente";

				} else {
					return "Error al guardar, experiencia laboral existente";

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
	public List<ExperienciaLaboralDTO> listarexperiencia() {
		System.out.println("entro listar ponencias");

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM experiencias_laborales");
			List<ExperienciaLaboralDTO> listado = new ArrayList<ExperienciaLaboralDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				ExperienciaLaboralDTO asistenciacursosDTO = new ExperienciaLaboralDTO();

				asistenciacursosDTO.setId(rs.getInt("id_experiencia_laboral"));
				asistenciacursosDTO.setPeriodo(rs.getString("periodo"));

				asistenciacursosDTO.setInstitucion(rs.getString("intitucion_empresa"));
				asistenciacursosDTO.setDpto(rs.getString("dpto_facultad"));
				asistenciacursosDTO.setLugar(rs.getString("lugar"));
				asistenciacursosDTO.setPuesto(rs.getString("puesto"));

				asistenciacursosDTO.setEvidencia((StreamedContent) rs.getBinaryStream("evidencia"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));

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
			System.out.println("Error en listarexperiencia()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarAsistenciaCurso(ExperienciaLaboralDTO experiencialaboralDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_experiencialaboral(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, experiencialaboralDTO.getId());
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

	public String ActualizarLicenciatura(ExperienciaLaboralDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_experiencialaboral(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(4, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(5, asistenciacursosDTO.getDpto());
				callableStatement.setString(6, asistenciacursosDTO.getLugar());
				callableStatement.setString(7, asistenciacursosDTO.getPuesto());
				callableStatement.setBinaryStream(8, (InputStream) asistenciacursosDTO.getEvidencia());
				callableStatement.setInt(9, 1);
				callableStatement.setInt(10, 1);
				callableStatement.setInt(11, 1);

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
