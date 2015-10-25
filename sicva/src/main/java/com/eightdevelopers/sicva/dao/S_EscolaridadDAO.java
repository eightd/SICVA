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
import com.eightdevelopers.sicva.dto.EscolaridadDTO;

/**
 * 
 * @author David Candia
 *
 */

public class S_EscolaridadDAO {

	public String obtenerValorSesion(String clave) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			String bd = (String) context.getExternalContext().getSessionMap().get(clave);
			return bd;
		} catch (Exception ex) {
			return "";
		}
	}

	//LISTAR
	public List<EscolaridadDTO> ListarEscolaridades() {
		System.out.println("Listando S_escolaridades");
		String valor = (obtenerValorSesion("lic"));
		int lic_session = Integer.parseInt(valor);
		try {
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection
					.prepareStatement("SELECT CONCAT(u.nombre, ' ',u.apellido_paterno,' ',u.apellido_materno) AS nombre, "
							+"CONCAT(um.nombre, ' ',um.apellido_paterno,' ',um.apellido_materno) AS nombre_modi, "
							+ "e.*, r.descripcion AS rol, n.descripcion AS nivel, CASE WHEN e.evidencia > '' "
							+ "THEN 'SI' " + "ELSE 'NO' " + "END existencia " + "FROM escolaridades e "
							+ "INNER JOIN usuarios u " + "ON u.id_usuarios=e.usuarios_id_usuarios "
							+ "INNER JOIN usuarios um "+ "ON um.id_usuarios=e.id_usuario_modificacion "
							+ "INNER JOIN roles r " + "ON u.roles_id_rol=r.id_rol " + "INNER JOIN niveles_educativos n "
							+ "ON e.niveles_educativos_id_nivel_educativo=n.id_nivel_educativo "
							+ "WHERE e.id_lic_inscrita=" + lic_session + " " + "ORDER BY u.apellido_paterno");

			List<EscolaridadDTO> listado = new ArrayList<EscolaridadDTO>();
			ResultSet rs = ps.executeQuery();
			boolean found = false;
			while (rs.next()) {
				EscolaridadDTO escolaridadesDTO = new EscolaridadDTO();
				escolaridadesDTO.setNombre(rs.getString("nombre"));
				escolaridadesDTO.setNombreodif(rs.getString("nombre_modi"));
				escolaridadesDTO.setId(rs.getInt("id_escolaridad"));
				escolaridadesDTO.setInstitucion(rs.getString("institucion"));
				escolaridadesDTO.setPeriodo(rs.getString("periodo"));
				escolaridadesDTO.setLugar(rs.getString("lugar"));
				escolaridadesDTO.setDocumento(rs.getString("documento_obtenido"));
				escolaridadesDTO.setIdmodif(rs.getInt("id_usuario_modificacion"));
				escolaridadesDTO.setIdlic(rs.getInt("id_lic_inscrita"));
				escolaridadesDTO.setId_nivel(rs.getInt("niveles_educativos_id_nivel_educativo"));
				escolaridadesDTO.setIdusuario(rs.getInt("usuarios_id_usuarios"));
				escolaridadesDTO.setRol(rs.getString("rol"));
				escolaridadesDTO.setNivel(rs.getString("nivel"));
				escolaridadesDTO.setExistencia(rs.getString("existencia"));
				listado.add(escolaridadesDTO);
				found = true;

			}
			rs.close();
			if (found) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listar escolaridades()" + e.getMessage());
			return null;
		}

	}

	// ELIMINAR
	public String EliminarEscolaridad(EscolaridadDTO escolaridadDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_escolaridad(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridadDTO.getId());
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
			System.out.println("Error en EliminarEscolaridad()..." + ex.getMessage());
		}
		return "";
	}
	
	//ACTUALIZAR EVIDENCIA 
	
	public String ActualizarEvidencia(EscolaridadDTO escolaridadDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_evidencia_escolaridad(?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridadDTO.getId());
				callableStatement.setInt(3, idUsuMod);
				callableStatement.setBinaryStream(4, escolaridadDTO.getEvidencia().getInputstream());
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
	
	//ACTUAZLIZAR 
	public String ActualizarEscolaridad(EscolaridadDTO escolaridadDTO, int idUsuMod) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call actualizar_escolaridad(?,?,?,?,?,?,?,?,?)} ";

				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, escolaridadDTO.getId());
				callableStatement.setInt(3, escolaridadDTO.getId_nivel());
				callableStatement.setString(4, escolaridadDTO.getInstitucion());
				callableStatement.setString(5, escolaridadDTO.getPeriodo());
				callableStatement.setString(6, escolaridadDTO.getLugar());
				callableStatement.setString(7, escolaridadDTO.getDocumento());;
				callableStatement.setInt(8, idUsuMod);
				callableStatement.setInt(9, escolaridadDTO.getIdlic());
				callableStatement.setInt(10, escolaridadDTO.getIdusuario());

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
			System.out.println("Error en ActualizarEscolaridad()..." + ex.getMessage());
		}

		return "";
	}

}
