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
import com.eightdevelopers.sicva.dto.PonenciasDTO;
 /**
  * 
  * @author David Candia
  *
  */
public class S_PonenciasDAO {

	// LISTAR
	public List<PonenciasDTO> listarPonencias() {
		System.out.println("entro listar ponencias");

		try {
			String valor = obtenerValorSesion("lic");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT p.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
					+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
					+ "CASE WHEN p.evidencia > '' THEN 'SI' ELSE 'NO' END existencia "
					+ "FROM cursos_ponencias p "
					+ "INNER JOIN usuarios u ON u.id_usuarios = p.usuarios_id_usuarios "
					+ "INNER JOIN usuarios um ON um.id_usuarios = p.id_usuario_modificacion "
					+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol "
					+ "WHERE p.id_lic_inscrita = ? ORDER BY u.apellido_paterno; ");

			List<PonenciasDTO> listado = new ArrayList<PonenciasDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				PonenciasDTO ponenciasDTO = new PonenciasDTO();

				ponenciasDTO.setId(rs.getInt("id_curso_ponencia"));
				ponenciasDTO.setPeriodo(rs.getString("periodo"));
				ponenciasDTO.setCurso(rs.getString("curso"));
				ponenciasDTO.setInstitucion(rs.getString("institucion"));
				ponenciasDTO.setLugar(rs.getString("lugar"));
				ponenciasDTO.setHoras(rs.getString("horas"));
				ponenciasDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				ponenciasDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				ponenciasDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				ponenciasDTO.setFechamodif(rs.getString("fecha_modificacion"));
				ponenciasDTO.setName(rs.getString("nombre"));
				ponenciasDTO.setNombreodif(rs.getString("nombre_modi"));
				ponenciasDTO.setRol(rs.getString("descripcion"));
				ponenciasDTO.setExistencia(rs.getString("existencia"));

				listado.add(ponenciasDTO);

				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarponencia()" + e.getMessage());
			return null;
		}

	}
	// ELIMINAR
	public String EliminarPonencia(PonenciasDTO ponenciasDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_cursosponencias(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, ponenciasDTO.getId());
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
			System.out.println("Error en EliminarPonencia()..." + ex.getMessage());
		}
		return "";
	}

	// ACTUALIZAR
	public String ActualizarPonencia(PonenciasDTO ponenciasDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_cursosponencias(?,?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, ponenciasDTO.getId());
				callableStatement.setString(3, ponenciasDTO.getPeriodo());
				callableStatement.setString(4, ponenciasDTO.getCurso());
				callableStatement.setString(5, ponenciasDTO.getInstitucion());
				callableStatement.setString(6, ponenciasDTO.getLugar());
				callableStatement.setString(7, ponenciasDTO.getHoras());
				callableStatement.setInt(8, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(9,Fecha);
				callableStatement.setInt(10, ponenciasDTO.getIdlic());
				callableStatement.setInt(11, ponenciasDTO.getIdusuario());

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
			System.out.println("Error en ActualizarPonencia()..." + ex.getMessage());
		}
		return "";
	}

	// ACTUALIZAR EVIDENCIA
	public String ActualizarEvidencia(PonenciasDTO ponenciasDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_cursosponencias(?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, ponenciasDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				Calendar cal1 = Calendar.getInstance();
				String Fecha = ("" + cal1.get(Calendar.DATE) + "/" + cal1.get(Calendar.MONTH) + "/" + cal1.get(Calendar.YEAR) + " "
						+ cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND) + ":" + cal1
						.get(Calendar.MILLISECOND));
				callableStatement.setString(4,Fecha);
				callableStatement.setBinaryStream(5, ponenciasDTO.getEvidencia().getInputstream());
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
