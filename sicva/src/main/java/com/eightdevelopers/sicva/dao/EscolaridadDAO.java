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
import com.eightdevelopers.sicva.dto.EscolaridadDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'Asistencia_Cursos' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres y Carlos Ricardo Hernï¿½ndez Reyes
 *
 */

// GUARDAR

public class EscolaridadDAO {
	public String guardarDato(EscolaridadDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_escolaridad(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getNivel());
				callableStatement.setString(3, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(4, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(5, asistenciacursosDTO.getLugar());
				callableStatement.setString(6, asistenciacursosDTO.getDocumento());
				// Se cambia el modo de introduccir la imagen de (InputStream)
				callableStatement.setBinaryStream(7, asistenciacursosDTO.getEvidencia().getInputstream());
				callableStatement.setInt(8, asistenciacursosDTO.getIdmodif());
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(9,Fecha);
				callableStatement.setInt(10, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(11, asistenciacursosDTO.getIdusuario());

				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "¡Se guardo satisfactoriamente!";

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
	public List<EscolaridadDTO> listarasistenciacursos() {
		System.out.println("entra a listar");
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
							+ "FROM escolaridades e INNER JOIN licenciaturas nn ON e.id_lic_inscrita=nn.id_licenciatura "
							+ "INNER JOIN niveles_educativos n ON e.niveles_educativos_id_nivel_educativo=n.id_nivel_educativo "
							+ "INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios WHERE e.usuarios_id_usuarios=?");
			List<EscolaridadDTO> listado = new ArrayList<EscolaridadDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				EscolaridadDTO escolaridadDTO = new EscolaridadDTO();

				escolaridadDTO.setId(rs.getInt("id_escolaridad"));
				escolaridadDTO.setNivel(rs.getString("niveles_educativos_id_nivel_educativo"));
				escolaridadDTO.setInstitucion(rs.getString("institucion"));
				escolaridadDTO.setPeriodo(rs.getString("periodo"));
				escolaridadDTO.setLugar(rs.getString("lugar"));
				escolaridadDTO.setDocumento(rs.getString("documento_obtenido"));
				escolaridadDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				escolaridadDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				escolaridadDTO.setDescripcionlic(rs.getString("nn.descripcion"));
				escolaridadDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				escolaridadDTO.setId_nivel(rs.getInt("id_nivel_educativo"));
				escolaridadDTO.setFechamodif(rs.getString("fecha_modificacion"));
				escolaridadDTO.setDescripcion(rs.getString("n.descripcion"));
				escolaridadDTO.setNombreodif(rs.getString("nombre_modi"));
				escolaridadDTO.setExistencia(rs.getString("exitencia"));

				listado.add(escolaridadDTO);

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
	public String EliminarAsistenciaCurso(EscolaridadDTO escolaridaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_escolaridad(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridaDTO.getId());
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

	public String ActualizarLicenciatura(EscolaridadDTO asistenciacursosDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_escolaridad(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getNivel());
				callableStatement.setString(4, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(5, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(6, asistenciacursosDTO.getLugar());
				callableStatement.setString(7, asistenciacursosDTO.getDocumento());
				callableStatement.setInt(8, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(9,Fecha);
				callableStatement.setInt(10, asistenciacursosDTO.getIdlic());
				callableStatement.setInt(11, asistenciacursosDTO.getIdusuario());

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
	
	//ACTUALIZAR EVIDENCIA 
	
		public String ActualizarEvidencia(EscolaridadDTO escolaridaDTO, int idUsuMod) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_evidencia_escolaridad(?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, escolaridaDTO.getId());
					callableStatement.setInt(3, idUsuMod);
					
					Calendar cal1 = Calendar.getInstance();
					String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
							+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
							.get(Calendar.MILLISECOND));
					callableStatement.setString(4,Fecha);
					callableStatement.setBinaryStream(5, escolaridaDTO.getEvidencia().getInputstream());
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
