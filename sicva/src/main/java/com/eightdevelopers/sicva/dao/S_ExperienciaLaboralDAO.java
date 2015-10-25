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
import com.eightdevelopers.sicva.dto.ExperienciaLaboralDTO;

/**
 * 
 * @author David Candia
 *
 */
public class S_ExperienciaLaboralDAO {

	// LISTAR LICENCIATURAS
	/**
	 * 
	 * @return
	 */
	public List<ExperienciaLaboralDTO> listarExperiencias() {
		System.out.println("entro listar experincias");
		try {
			String valor = obtenerValorSesion("lic");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT e.* ,CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+ "CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, r.descripcion, "
							+ "CASE WHEN e.evidencia > '' THEN 'SI' ELSE 'NO' END existencia "
							+ "FROM experiencias_laborales e "
							+ "INNER JOIN usuarios u ON u.id_usuarios = e.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um ON um.id_usuarios = e.id_usuario_modificacion "
							+ "INNER JOIN roles r ON r.id_rol = u.roles_id_rol "
							+ "WHERE e.id_lic_inscrita = ? ORDER BY u.apellido_paterno");
			List<ExperienciaLaboralDTO> listado = new ArrayList<ExperienciaLaboralDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				ExperienciaLaboralDTO experiencialaboralDTO = new ExperienciaLaboralDTO();

				experiencialaboralDTO.setId(rs.getInt("id_experiencia_laboral"));
				experiencialaboralDTO.setPeriodo(rs.getString("periodo"));
				experiencialaboralDTO.setInstitucion(rs.getString("intitucion_empresa"));
				experiencialaboralDTO.setDpto(rs.getString("dpto_facultad"));
				experiencialaboralDTO.setLugar(rs.getString("lugar"));
				experiencialaboralDTO.setPuesto(rs.getString("puesto"));
				experiencialaboralDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				experiencialaboralDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				experiencialaboralDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				experiencialaboralDTO.setNombre(rs.getString("nombre"));
				experiencialaboralDTO.setNombreodif(rs.getString("nombre_modi"));
				experiencialaboralDTO.setRol(rs.getString("descripcion"));
				experiencialaboralDTO.setExistencia(rs.getString("existencia"));
				listado.add(experiencialaboralDTO);

				found = true;
			}
			rs.close();
			if (found) {
				return listado;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error en listarExperiencias()" + e.getMessage());
			return null;
		}

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

	// ELIMINAR
	public String EliminarExperiencia(ExperienciaLaboralDTO experiencialaboralDTO) {
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
			System.out.println("Error en EliminarExperiencia()..." + ex.getMessage());
		}

		return "";

	}

	// ACTUALIZAR
	public String ActualizarExperiencia(ExperienciaLaboralDTO experiencialaboralDTO, int IdUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_experiencialaboral(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, experiencialaboralDTO.getId());
				callableStatement.setString(3, experiencialaboralDTO.getPeriodo());
				callableStatement.setString(4, experiencialaboralDTO.getInstitucion());
				callableStatement.setString(5, experiencialaboralDTO.getDpto());
				callableStatement.setString(6, experiencialaboralDTO.getLugar());
				callableStatement.setString(7, experiencialaboralDTO.getPuesto());
				callableStatement.setInt(8, IdUsuMod);
				callableStatement.setInt(9, experiencialaboralDTO.getIdlic());
				callableStatement.setInt(10, experiencialaboralDTO.getIdusuario());

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
	public String ActualizarEvidencia(ExperienciaLaboralDTO experiencialaboralDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_experiencialaboral(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, experiencialaboralDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, experiencialaboralDTO.getEvidencia().getInputstream());
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
