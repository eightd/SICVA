package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.TesisDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Tesis' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres y Carlos Ricardo Hern√°ndez Reyes
 *
 */

// GUARDAR

public class TesisDAO {
	public String guardarDato(TesisDTO tesisDTO) {
		try {
			try {

				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_tesis(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, tesisDTO.getProyecto());

				DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
				Date date = (Date) formatter.parse(tesisDTO.getFechatitulacion());
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DATE);

				callableStatement.setString(3, formatedDate);
				callableStatement.setString(4, tesisDTO.getAlumno());
				// Se cambia el modo de introduccir la imagen de (InputStream)
				// tesisDTO.getEvidencia()
				// a tesisDTO.getEvidencia().getInputstream()
				callableStatement.setBinaryStream(5, tesisDTO.getEvidencia().getInputstream());
				callableStatement.setInt(6, tesisDTO.getIdmodif());
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
					return "°Se guardo satisfactoriamente!";
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

	// LISTAR
	/**
	 * 
	 * @return
	 */
	public List<TesisDTO> listarasistenciacursos() {

		try {
			String valor = obtenerValorSesion("id");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT e.*, nn.descripcion,n.id_nivel_educativo ,n.descripcion, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, "
							+ "CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END exitencia "
							+ "FROM asesorias_de_tesis e "
							+ "INNER JOIN licenciaturas nn ON e.id_lic_inscrita=nn.id_licenciatura "
							+ "INNER JOIN niveles_educativos n ON e.niveles_educativos_id_nivel_educativo=n.id_nivel_educativo "
							+ "INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios "
							+ "WHERE e.usuarios_id_usuarios=?");

			List<TesisDTO> listado = new ArrayList<TesisDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				TesisDTO asistenciacursosDTO = new TesisDTO();

				asistenciacursosDTO.setId(rs.getInt("id_asesorias_tesis"));
				asistenciacursosDTO.setProyecto(rs.getString("proyecto"));
				asistenciacursosDTO.setFechatitulacion(rs.getString("fecha_de_titulacion"));
				asistenciacursosDTO.setAlumno(rs.getString("alumno"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setDescripcionlic(rs.getString("nn.descripcion"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setFechamodif(rs.getString("fecha_modificacion"));
				asistenciacursosDTO.setNivel(rs.getInt("niveles_educativos_id_nivel_educativo"));
				asistenciacursosDTO.setId_nivel(rs.getInt("id_nivel_educativo"));
				asistenciacursosDTO.setDescripcion(rs.getString("n.descripcion"));
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

	public String ActualizarLicenciatura(TesisDTO asistenciacursosDTO, int IdUsuMod) {
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
				callableStatement.setInt(6, IdUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(7, Fecha);
				callableStatement.setInt(8, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(9, asistenciacursosDTO.getIdusuario());
				callableStatement.setInt(10, asistenciacursosDTO.getNivel());

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
				String Fecha= ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":"
						+ cal1.get(Calendar.MILLISECOND));
				callableStatement.setString(4, Fecha);
				callableStatement.setBinaryStream(5, tesisDTO.getEvidencia().getInputstream());
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
