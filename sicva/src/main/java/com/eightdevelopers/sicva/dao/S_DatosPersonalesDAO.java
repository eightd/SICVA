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
import com.eightdevelopers.sicva.dto.DatosPersonalesDTO;

public class S_DatosPersonalesDAO {

	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	// LISTAR
	public List<DatosPersonalesDTO> ListarDatosPersonales() {
		System.out.println("Listando S_datosPersonales");
		String licSesion = (obtenerValorSesion("lic"));
		int lic_session = Integer.parseInt(licSesion);
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT d.*, r.descripcion AS rol, "
					+ "CASE WHEN d.foto > '' THEN 'SI' ELSE 'NO' END existencia, "
					+ "CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS name, "
					+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi "
					+ "FROM datos_personales d "
					+ "INNER JOIN vinculaciones v ON d.usuarios_id_usuarios=v.usuarios_id_usuarios "
					+ "INNER JOIN usuarios u ON d.usuarios_id_usuarios=u.id_usuarios "
					+ "INNER JOIN usuarios um ON d.id_usuario_modificacion=um.id_usuarios "
					+ "INNER JOIN roles r ON u.roles_id_rol=r.id_rol "
					+ "AND v.licenciaturas_id_licenciatura=?");

			List<DatosPersonalesDTO> listado = new ArrayList<DatosPersonalesDTO>();
			ps.setInt(1, lic_session);
			ResultSet rs = ps.executeQuery();
			boolean found = false;
			while (rs.next()) {
				DatosPersonalesDTO datospersonalesDTO = new DatosPersonalesDTO();
				datospersonalesDTO.setId(rs.getInt("id_datos_personales"));
				datospersonalesDTO.setNombre(rs.getString("nombre"));
				datospersonalesDTO.setAp(rs.getString("apellido_paterno"));
				datospersonalesDTO.setAm(rs.getString("apellido_materno"));
				datospersonalesDTO.setCurp(rs.getString("curp"));
				datospersonalesDTO.setRfc(rs.getString("rfc"));
				datospersonalesDTO.setFechanacimiento(rs.getString("fecha_de_nacimiento"));
				datospersonalesDTO.setNacionalidad(rs.getString("nacionalidad"));
				datospersonalesDTO.setDomicilio(rs.getString("domicilio"));
				datospersonalesDTO.setCel(rs.getString("telefono_movil"));
				datospersonalesDTO.setTel(rs.getString("telefono_casa"));
				datospersonalesDTO.setEmail(rs.getString("email"));
				datospersonalesDTO.setFacebook(rs.getString("facebook"));
				datospersonalesDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				datospersonalesDTO.setFechamodif(rs.getString("d.fecha_modificacion"));
				datospersonalesDTO.setIdestadocivil(rs.getInt("estados_civiles_id_estados_civiles"));
				datospersonalesDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				datospersonalesDTO.setRol(rs.getString("rol"));
				datospersonalesDTO.setExistencia(rs.getString("existencia"));
				datospersonalesDTO.setName(rs.getString("name"));
				datospersonalesDTO.setNombreodif(rs.getString("nombre_modi"));
				listado.add(datospersonalesDTO);
				found = true;
			}
			rs.close();
			if (found) {
				return listado;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error en ListarDatosPersonales()" + e.getMessage());
			return null;
		}
	}
	
	// ELIMINAR
		public String EliminarDatos(DatosPersonalesDTO datosPersonalesDTO) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call eliminar_datospersonales(?)} ";
					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, datosPersonalesDTO.getId());
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

		public String ActualizarDatos(DatosPersonalesDTO datospersonalesDTO, int IdUsuMod) {
			try {
				try {
					ConexionBD conexionBD = new ConexionBD();
					conexionBD.abrir();
					Connection connection = conexionBD.getConexion();

					String sql = "{ ? = call actualizar_datospersonales(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)} ";

					CallableStatement callableStatement = connection.prepareCall(sql);
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.setInt(2, datospersonalesDTO.getId());
					callableStatement.setString(3, datospersonalesDTO.getNombre());
					callableStatement.setString(4, datospersonalesDTO.getAp());
					callableStatement.setString(5, datospersonalesDTO.getAm());
					callableStatement.setString(6, datospersonalesDTO.getCurp());
					callableStatement.setString(7, datospersonalesDTO.getRfc());
					callableStatement.setString(8, datospersonalesDTO.getFechanacimiento());
					callableStatement.setString(9, datospersonalesDTO.getNacionalidad());
					callableStatement.setString(10, datospersonalesDTO.getDomicilio());
					callableStatement.setString(11, datospersonalesDTO.getCel());
					callableStatement.setString(12, datospersonalesDTO.getTel());
					callableStatement.setString(13, datospersonalesDTO.getEmail());
					callableStatement.setString(14, datospersonalesDTO.getFacebook());
					callableStatement.setInt(15, IdUsuMod);
					Calendar cal1 = Calendar.getInstance();
					String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
							+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
							.get(Calendar.MILLISECOND));
					callableStatement.setString(16,Fecha);
					callableStatement.setInt(17, datospersonalesDTO.getIdestadocivil());
					callableStatement.setInt(18, datospersonalesDTO.getIdusuario());
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

}
