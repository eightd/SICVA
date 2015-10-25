package com.eightdevelopers.sicva.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.dto.LicenciaturaDTO;

/**
 * Clase que permite hacer el CRUD de la tabla 'licenciaturas' de la Base de
 * Datos SICVA
 * 
 * @author Elizabeth Espinoza
 *
 */
public class LicenciaturaDAO {

	/**
	 * Método que permite insertar un nuevo registro a la tabla 'licenciaturas'
	 * de la BD SICVA
	 * 
	 * @param datos
	 *            de tipo licenciaturaDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */
	public String guardarDato(LicenciaturaDTO licenciaturaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call insertar_licenciatura(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setString(2, licenciaturaDTO.getDescripcion());
				callableStatement.execute();
				Integer num = callableStatement.getInt(1);
				connection.close();
				if (num == 1) {

					return "¡Se registro satisfactoriamente!";

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

	/**
	 * Método que permite realizar una consulta a la tabla 'licenciaturas' y
	 * obtener un listado de los datos registrados
	 * 
	 * @return arreglo de tipo LicenciaturaDTO
	 */
	public List<LicenciaturaDTO> listarlicenciatura() {

		try {

			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();
			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM licenciaturas WHERE estado=1 ORDER BY descripcion");
			List<LicenciaturaDTO> listado = new ArrayList<LicenciaturaDTO>();
			ResultSet rs = ps.executeQuery();

			boolean found = false;

			while (rs.next()) {
				LicenciaturaDTO licenciaturaDTO = new LicenciaturaDTO();

				licenciaturaDTO.setId(rs.getInt("id_licenciatura"));
				licenciaturaDTO.setDescripcion(rs.getString("descripcion"));

				listado.add(licenciaturaDTO);
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

	/**
	 * Método que permite eliminar un registro de la tabla 'licenciaturas' de la
	 * BD SICVA
	 * 
	 * @param datos
	 *            de tipo licenciaturaDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */

	public String EliminarLicen(LicenciaturaDTO licenciaturaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();

				String sql = "{ ? = call eliminar_licenciatura(?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, licenciaturaDTO.getId());
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

	/**
	 * Método que permite actualizar los registros de la tabla 'licenciaturas'
	 * de la BD SICVA
	 * 
	 * @param datos
	 *            de tipo licenciaturaDTO
	 * @return true si la accion se completo con exito, de lo contario regresa
	 *         false
	 */

	public String ActualizarLicenciatura(LicenciaturaDTO licenciaturaDTO) {
		try {
			try {
				ConexionBD conexionBD = new ConexionBD();
				conexionBD.abrir();
				Connection connection = conexionBD.getConexion();
				String sql = "{ ? = call actualizar_licenciatura(?,?)} ";
				CallableStatement callableStatement = connection.prepareCall(sql);
				callableStatement.registerOutParameter(1, Types.INTEGER);
				callableStatement.setInt(2, licenciaturaDTO.getId());
				callableStatement.setString(3, licenciaturaDTO.getDescripcion());

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

}
