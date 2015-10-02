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
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Datos_Personales' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres
 *
 */

// GUARDAR

public class DatosPersonalesDAO {
	public String guardarDato(DatosPersonalesDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_datospersonales(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getNombre());
				callableStatement.setString(3, asistenciacursosDTO.getAp());
				callableStatement.setString(4, asistenciacursosDTO.getAm());
				callableStatement.setString(5, asistenciacursosDTO.getCurp());
				callableStatement.setString(6, asistenciacursosDTO.getRfc());
				callableStatement.setString(7, asistenciacursosDTO.getFechanacimiento());
				callableStatement.setString(8, asistenciacursosDTO.getNacionalidad());
				callableStatement.setString(9, asistenciacursosDTO.getDomicilio());
				callableStatement.setString(10, asistenciacursosDTO.getCel());
				callableStatement.setString(11, asistenciacursosDTO.getTel());
				callableStatement.setString(12, asistenciacursosDTO.getEmail());
				callableStatement.setString(13, asistenciacursosDTO.getFacebook());
				callableStatement.setBinaryStream(14, (InputStream) asistenciacursosDTO.getFoto());
				callableStatement.setInt(15, 1);
				callableStatement.setInt(16, 1);
				callableStatement.setInt(17, asistenciacursosDTO.getIdestadocivil());
				callableStatement.setInt(18, 1);

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "Se ha guardado satisfactoriamente";

				} else {
					return "Error al guardar, datos personales existente";

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
	public List<DatosPersonalesDTO> listarasistenciacursos() {

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM datos_personales");
			List<DatosPersonalesDTO> listado = new ArrayList<DatosPersonalesDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				DatosPersonalesDTO asistenciacursosDTO = new DatosPersonalesDTO();

				asistenciacursosDTO.setId(rs.getInt("id_datos_personales"));
				asistenciacursosDTO.setNombre(rs.getString("nombre"));
				asistenciacursosDTO.setAp(rs.getString("apellido_paterno"));
				asistenciacursosDTO.setAm(rs.getString("apellido_materno"));
				asistenciacursosDTO.setCurp(rs.getString("curp"));
				asistenciacursosDTO.setRfc(rs.getString("rfc"));

				asistenciacursosDTO.setFechanacimiento(rs.getString("fecha_de_nacimiento"));
				asistenciacursosDTO.setNacionalidad(rs.getString("nacionalidad"));
				asistenciacursosDTO.setDomicilio(rs.getString("domicilio"));
				asistenciacursosDTO.setCel(rs.getString("telefono_movil"));
				asistenciacursosDTO.setTel(rs.getString("telefono_casa"));

				asistenciacursosDTO.setEmail(rs.getString("email"));
				asistenciacursosDTO.setFacebook(rs.getString("facebook"));

				asistenciacursosDTO.setFoto((StreamedContent) rs.getBinaryStream("foto"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setIdestadocivil(rs.getInt("estados_civiles_id_estados_civiles"));
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
			System.out.println("Error en listarlicenciatura()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarAsistenciaCurso(DatosPersonalesDTO escolaridaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_datospersonales(?)} ";
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

	public String ActualizarLicenciatura(DatosPersonalesDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_datospersonales(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());

				callableStatement.setString(3, asistenciacursosDTO.getNombre());
				callableStatement.setString(4, asistenciacursosDTO.getAp());
				callableStatement.setString(5, asistenciacursosDTO.getAm());
				callableStatement.setString(6, asistenciacursosDTO.getCurp());
				callableStatement.setString(7, asistenciacursosDTO.getRfc());
				callableStatement.setString(8, asistenciacursosDTO.getFechanacimiento());
				callableStatement.setString(9, asistenciacursosDTO.getNacionalidad());
				callableStatement.setString(10, asistenciacursosDTO.getDomicilio());
				callableStatement.setString(11, asistenciacursosDTO.getCel());
				callableStatement.setString(12, asistenciacursosDTO.getTel());
				callableStatement.setString(13, asistenciacursosDTO.getEmail());
				callableStatement.setString(14, asistenciacursosDTO.getFacebook());
				callableStatement.setBinaryStream(15, (InputStream) asistenciacursosDTO.getFoto());
				callableStatement.setInt(16, 1);
				callableStatement.setInt(17, 1);
				callableStatement.setInt(18, asistenciacursosDTO.getIdestadocivil());
				callableStatement.setInt(19, 1);

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
