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

				String sql = "{ ? = call insertar_experiencialaboral(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, asistenciacursosDTO.getPeriodo());
				callableStatement.setString(3, asistenciacursosDTO.getInstitucion());
				callableStatement.setString(4, asistenciacursosDTO.getDpto());
				callableStatement.setString(5, asistenciacursosDTO.getLugar());
				callableStatement.setString(6, asistenciacursosDTO.getPuesto());
			
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
	public List<ExperienciaLaboralDTO> listarexperiencia() {
		System.out.println("entro listar ponencias");

		try {
			String valor=obtenerValorSesion("id");
            int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT e.*, nn.descripcion,"
					+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno)"
					+ " AS nombre_modi, CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END exitencia "
					+ "FROM experiencias_laborales e "
					+ "INNER JOIN licenciaturas nn ON e.id_lic_inscrita=nn.id_licenciatura "
					+ "INNER JOIN usuarios um ON e.id_usuario_modificacion=um.id_usuarios "
					+ "WHERE e.usuarios_id_usuarios=?");
			List<ExperienciaLaboralDTO> listado = new ArrayList<ExperienciaLaboralDTO>();
			id_session=Integer.parseInt(valor);
			ps.setInt(1,id_session);
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

	public String ActualizarLicenciatura(ExperienciaLaboralDTO asistenciacursosDTO, int IdUsuMod) {
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
				callableStatement.setInt(8, IdUsuMod);
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
			public String ActualizarEvidencia(ExperienciaLaboralDTO experienciaDTO, int idUsuMod) {
				try {
					try {
						ConexionBD conexionBD = new ConexionBD();
						conexionBD.abrir();
						Connection connection = conexionBD.getConexion();

						String sql = "{ ? = call actualizar_evidencia_experiencialaboral(?,?,?,?)} ";

						CallableStatement callableStatement = connection.prepareCall(sql);
						callableStatement.registerOutParameter(1, Types.INTEGER);
						callableStatement.setInt(2, experienciaDTO.getId());
						callableStatement.setInt(3, idUsuMod);
						Calendar cal1 = Calendar.getInstance();
						String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
								+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
								.get(Calendar.MILLISECOND));
						
						callableStatement.setString(4,Fecha);
						callableStatement.setBinaryStream(5, experienciaDTO.getEvidencia().getInputstream());
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
