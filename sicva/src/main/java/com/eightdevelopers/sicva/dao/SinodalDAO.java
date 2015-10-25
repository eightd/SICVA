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
import com.eightdevelopers.sicva.dto.SinodalDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Sinodal' de la Base de Datos
 * 'SICVA'
 * 
 * @author Fernando Torres y Carlos Ricardo Hern√°ndez Reyes
 *
 */

// GUARDAR

public class SinodalDAO {
	public String guardarDato(SinodalDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_sinodal(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getProyecto());

				DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
				Date date = (Date) formatter.parse(asistenciacursosDTO.getFechatitulacion());
				System.out.println(date);
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DATE);

				callableStatement.setString(3, formatedDate);
				callableStatement.setString(4, asistenciacursosDTO.getAlumno());
				
				// Se cambia el modo de introduccir la imagen de (InputStream)
				// asistenciacursosDTO.getEvidencia()
				// a asistenciacursosDTO.getEvidencia().getInputstream()
				callableStatement.setBinaryStream(5, asistenciacursosDTO.getEvidencia().getInputstream());
				callableStatement.setInt(6, asistenciacursosDTO.getIdmodif());
				callableStatement.setInt(7, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(8, asistenciacursosDTO.getIdopc());
				callableStatement.setInt(9, asistenciacursosDTO.getIdusuario());
				callableStatement.setInt(10, asistenciacursosDTO.getNivel());

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
	public List<SinodalDTO> listarasistenciacursos() {
		System.out.println("Entro lista sinodal");
		try {
			String valor = obtenerValorSesion("id");
			int id_session;

			System.out.println("VALOR" + valor);
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT e.*, nn.descripcion,n.id_nivel_educativo ,n.descripcion,o.descripcion, o.id_opciones_de_titulacion, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, "
							+ "CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END exitencia "
							+ "FROM sinodales e "
							+ "INNER JOIN licenciaturas nn ON e.id_lic_inscrita=nn.id_licenciatura "
							+ "INNER JOIN niveles_educativos n ON e.niveles_educativos_id_nivel_educativo=n.id_nivel_educativo "
							+ "INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios "
							+ "INNER JOIN opciones_de_titulacion o on e.opciones_de_titulacion_id_opciones_de_titulacion=o.id_opciones_de_titulacion "
							+ "WHERE e.usuarios_id_usuarios=?");

			List<SinodalDTO> listado = new ArrayList<SinodalDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				SinodalDTO asistenciacursosDTO = new SinodalDTO();

				asistenciacursosDTO.setId(rs.getInt("id_sinodal"));
				asistenciacursosDTO.setProyecto(rs.getString("proyecto"));
				asistenciacursosDTO.setFechatitulacion(rs.getString("fecha_de_titulacion"));
				asistenciacursosDTO.setAlumno(rs.getString("alumnos"));
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				asistenciacursosDTO.setDescripcionlic(rs.getString("nn.descripcion"));
				asistenciacursosDTO.setIdopc(rs.getInt("opciones_de_titulacion_id_opciones_de_titulacion"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setNivel(rs.getInt("niveles_educativos_id_nivel_educativo"));
				asistenciacursosDTO.setId_nivel(rs.getInt("id_nivel_educativo"));
				asistenciacursosDTO.setDescripcion(rs.getString("n.descripcion"));
				asistenciacursosDTO.setId_nivel_Opcion(rs.getInt("o.id_opciones_de_titulacion"));
				asistenciacursosDTO.setDescripcionTitulacion(rs.getString("o.descripcion"));
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
			System.out.println("Error en listarsinodales()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarAsistenciaCurso(SinodalDTO escolaridaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_sinodal(?)} ";
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

	public String ActualizarLicenciatura(SinodalDTO asistenciacursosDTO, int IdUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_sinodal(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getProyecto());
				callableStatement.setString(4, asistenciacursosDTO.getFechatitulacion());
				callableStatement.setString(5, asistenciacursosDTO.getAlumno());
				callableStatement.setInt(6, IdUsuMod);
				callableStatement.setInt(7, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(8, asistenciacursosDTO.getIdopc());
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

	public String ActualizarEvidencia(SinodalDTO sinodalDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_sinodal(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, sinodalDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, sinodalDTO.getEvidencia().getInputstream());
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
