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
import com.eightdevelopers.sicva.dto.ComisionesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Comisiones' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres y Carlos Ricardo Hern√°ndez Reyes
 *
 */

// GUARDAR

public class ComisionesDAO {
	public String guardarDato(ComisionesDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_comisiones(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getFecha());
				callableStatement.setString(3, asistenciacursosDTO.getEvento());
				callableStatement.setString(4, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(5, asistenciacursosDTO.getLugar());
				callableStatement.setString(6, asistenciacursosDTO.getParticipacion());
				// Se cambia el modo de introduccir la imagen de (InputStream)
				// asistenciacursosDTO.getEvidencia()
				// a asistenciacursosDTO.getEvidencia().getInputstream()
				callableStatement.setBinaryStream(7, asistenciacursosDTO.getEvidencia().getInputstream());
				callableStatement.setInt(8, asistenciacursosDTO.getIdmodif());
				callableStatement.setInt(9, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(10, asistenciacursosDTO.getIdusuario());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "°Se guardado satisfactoriamente!";

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

	// LISTAR LICENCIATURAS
	/**
	 * 
	 * @return
	 */
	public List<ComisionesDTO> listarasistenciacursos() {

		try {
			String valor = obtenerValorSesion("id");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT e.*, nn.descripcion, CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi,"
							+ "CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END exitencia  " + " FROM comisiones e "
							+ " INNER JOIN licenciaturas nn on e.id_lic_inscrita=nn.id_licenciatura "
							+ " INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios"
							+ " where usuarios_id_usuarios=?");
			List<ComisionesDTO> listado = new ArrayList<ComisionesDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				ComisionesDTO asistenciacursosDTO = new ComisionesDTO();

				asistenciacursosDTO.setId(rs.getInt("id_comision"));
				asistenciacursosDTO.setFecha(rs.getString("fecha"));
				asistenciacursosDTO.setEvento(rs.getString("evento"));
				asistenciacursosDTO.setInstitucion(rs.getString("institucion"));
				asistenciacursosDTO.setLugar(rs.getString("lugar"));
				asistenciacursosDTO.setParticipacion(rs.getString("participacion"));
				// asistenciacursosDTO.setEvidencia((StreamedContent)
				// rs.getBinaryStream("evidencia"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setDescripcionlic(rs.getString("descripcion"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setNombreodif(rs.getString("nombre_modi"));
				asistenciacursosDTO.setExistencia(rs.getString("exitencia"));

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
	public String EliminarAsistenciaCurso(ComisionesDTO escolaridaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_comisiones(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridaDTO.getId());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "°Se elimino satisfactoriamente!";
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
	public String ActualizarLicenciatura(ComisionesDTO asistenciacursosDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_comisiones(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getFecha());
				callableStatement.setString(4, asistenciacursosDTO.getEvento());
				callableStatement.setString(5, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(6, asistenciacursosDTO.getLugar());
				callableStatement.setString(7, asistenciacursosDTO.getParticipacion());
				callableStatement.setInt(8, idUsuMod);
				callableStatement.setInt(9, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(10, asistenciacursosDTO.getIdusuario());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "°Se actualizo satisfactoriamente!";
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

	public String ActualizarEvidencia(ComisionesDTO comisionesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_comisiones(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, comisionesDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, comisionesDTO.getEvidencia().getInputstream());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {
					return "°Se actualizo satisfactoriamente!";
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
