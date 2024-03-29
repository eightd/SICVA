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
import com.eightdevelopers.sicva.dto.AsistenciaCursosDTO;



/**
 * Clase que permite hacer el CRUD de la tabla 'Asistencia_Cursos' de la Base de
 * Datos 'SICVA'
 * 
 * @author Fernando Torres  y Carlos Ricardo Hernández Reyes
 *
 */

// GUARDAR

public class AsistenciaCursosDAO {
	public String guardarDato(AsistenciaCursosDTO asistenciacursosDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_asistenciascursos(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(3, asistenciacursosDTO.getCurso());
				callableStatement.setString(4, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(5, asistenciacursosDTO.getLugar());
				callableStatement.setString(6, asistenciacursosDTO.getHoras());
			    callableStatement.setBinaryStream(7,asistenciacursosDTO.getEvidencia().getInputstream());
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

					return "�Se guardado satisfactoriamente!";

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
	public List<AsistenciaCursosDTO> listarasistenciacursos() {
		try {
			String valor=obtenerValorSesion("id");
            int id_session;
			System.out.println("Entra a listar los cursos2");
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT e.*, nn.descripcion,CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi,"
					+ "CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END exitencia  "
					+ " FROM asistencias_cursos e "
					+ " INNER JOIN licenciaturas nn on e.id_lic_inscrita=nn.id_licenciatura"
					+ " INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios"
					+ " where usuarios_id_usuarios=?");
			List<AsistenciaCursosDTO> listado = new ArrayList<AsistenciaCursosDTO>();
			id_session=Integer.parseInt(valor);
			ps.setInt(1,id_session);
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
				asistenciacursosDTO.setDescripcionlic(rs.getString("descripcion"));
				asistenciacursosDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				asistenciacursosDTO.setFechamodif(rs.getString("fecha_modificacion"));
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
			System.out.println("Error en listarasistenciacursos()" + e.getMessage());
			return null;
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
					return "�Se elimino satisfactoriamente!";
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

	public String ActualizarLicenciatura(AsistenciaCursosDTO asistenciacursosDTO,int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_asistenciascursos(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, asistenciacursosDTO.getId());
				callableStatement.setString(3, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(4, asistenciacursosDTO.getCurso());
				callableStatement.setString(5, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(6, asistenciacursosDTO.getLugar());
				callableStatement.setString(7, asistenciacursosDTO.getHoras());
				callableStatement.setInt(8,idUsuMod);
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
					return "�Se actualizo satisfactoriamente!";
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
	
			public String ActualizarEvidencia(AsistenciaCursosDTO asistenciaCursosDTO, int idUsuMod) {
				try {
					try {
						ConexionBD conexionBD = new ConexionBD();
						conexionBD.abrir();
						Connection connection = conexionBD.getConexion();

						String sql = "{ ? = call actualizar_evidencia_asistenciascursos(?,?,?,?)} ";

						CallableStatement callableStatement = connection.prepareCall(sql);
						callableStatement.registerOutParameter(1, Types.INTEGER);
						callableStatement.setInt(2, asistenciaCursosDTO.getId());
						callableStatement.setInt(3, idUsuMod);
						Calendar cal1 = Calendar.getInstance();
						String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
								+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
								.get(Calendar.MILLISECOND));
						callableStatement.setString(4,Fecha);
						callableStatement.setBinaryStream(5, asistenciaCursosDTO.getEvidencia().getInputstream());
						callableStatement.execute();
						Integer num = callableStatement.getInt(1);
						connection.close();
						if (num == 1) {
							return "�Se actualizo satisfactoriamente!";
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