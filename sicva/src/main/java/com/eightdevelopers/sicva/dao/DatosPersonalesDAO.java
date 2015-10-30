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
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Datos_Personales' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres y Carlos Ricardo Hern√°ndez Reyes
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

				String sql = "{ ? = call insertar_datospersonales(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getNombre());
				callableStatement.setString(3, asistenciacursosDTO.getAp());
				callableStatement.setString(4, asistenciacursosDTO.getAm());
				callableStatement.setString(5, asistenciacursosDTO.getCurp());
				callableStatement.setString(6, asistenciacursosDTO.getRfc());
				DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
                Date date = (Date) formatter.parse(asistenciacursosDTO.getFechanacimiento());
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                String formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DATE);


				

				callableStatement.setString(7, formatedDate);
				callableStatement.setString(8, asistenciacursosDTO.getNacionalidad());
				callableStatement.setString(9, asistenciacursosDTO.getDomicilio());
				callableStatement.setString(10, asistenciacursosDTO.getCel());
				callableStatement.setString(11, asistenciacursosDTO.getTel());
				callableStatement.setString(12, asistenciacursosDTO.getEmail());
				callableStatement.setString(13, asistenciacursosDTO.getFacebook());
				callableStatement.setBinaryStream(14, asistenciacursosDTO.getFoto().getInputstream());
				callableStatement.setInt(15, asistenciacursosDTO.getIdmodif());
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(16,Fecha);
				callableStatement.setInt(17, asistenciacursosDTO.getIdestadocivil());
				callableStatement.setInt(18, asistenciacursosDTO.getIdusuario());
				callableStatement.setInt(19, asistenciacursosDTO.getIdGrado());
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

	// LISTAR DATOS PERSONALES
	/**
	 * 
	 * @return
	 */
	public List<DatosPersonalesDTO> listarasistenciacursos() {

		try {
			String valor = obtenerValorSesion("id");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT e.*, CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) "
							+ "AS nombre_modi,CASE WHEN e.foto > '' THEN 'SI' ELSE 'NO' END exitencia FROM datos_personales"
							+ " e INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios WHERE e.usuarios_id_usuarios=?");

			List<DatosPersonalesDTO> listado = new ArrayList<DatosPersonalesDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
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
				asistenciacursosDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				asistenciacursosDTO.setFechamodif(rs.getString("fecha_modificacion"));
				asistenciacursosDTO.setIdestadocivil(rs.getInt("estados_civiles_id_estados_civiles"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setNombreodif(rs.getString("nombre_modi"));
				asistenciacursosDTO.setExistencia(rs.getString("exitencia"));
				asistenciacursosDTO.setIdGrado(rs.getInt("grados_id_grado"));

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
			System.out.println("Error en listarDatosPersonales()" + e.getMessage());
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

	public String ActualizarLicenciatura(DatosPersonalesDTO asistenciacursosDTO, int IdUsuMod) {
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
				callableStatement.setInt(15, IdUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(16,Fecha);
				callableStatement.setInt(17, asistenciacursosDTO.getIdestadocivil());
				callableStatement.setInt(18, asistenciacursosDTO.getIdusuario());
				callableStatement.setInt(19, asistenciacursosDTO.getIdGrado());
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

	public String ActualizarEvidencia(DatosPersonalesDTO datospersonalesDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_datospersonales(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, datospersonalesDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(4,Fecha);
				callableStatement.setBinaryStream(5, datospersonalesDTO.getFoto().getInputstream());
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
