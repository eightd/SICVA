package com.eightdevelopers.sicva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import com.eightdevelopers.sicva.db.ConexionBD;
import com.eightdevelopers.sicva.db.SQLQueries;
import com.eightdevelopers.sicva.dto.CarreraDTO;
import com.eightdevelopers.sicva.dto.EstadoCivilDTO;
import com.eightdevelopers.sicva.dto.GradoDTO;
import com.eightdevelopers.sicva.dto.LicDTO;
import com.eightdevelopers.sicva.dto.NivelDTO;

public class CarreraDAO {

	public List<CarreraDTO> retrieveAlmacen() {

		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;

			List<CarreraDTO> listaCarreraDTO = new ArrayList<CarreraDTO>();
			ResultSet rs = null;

			CarreraDTO filaCarreraDTO = null;
			String query = "";
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);

			query = "SELECT * from opciones_de_titulacion";
			rs = sqlQueries.consulta(query);
			while (rs.next()) {

				filaCarreraDTO = new CarreraDTO();
				filaCarreraDTO.setId(rs.getInt(1));
				filaCarreraDTO.setDescripcion(rs.getString(2));
				
				listaCarreraDTO.add(filaCarreraDTO);

			}
			conexionBD.cerrar();
			return listaCarreraDTO;
		} catch (Exception ex) {
			System.out.println("CarreraDAO.retrieveAlmacen:" + ex.getMessage());
			return null;
		}
	}

	public List<EstadoCivilDTO> estadoCivil() {

		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;

			List<EstadoCivilDTO> listaEstadoDTO = new ArrayList<EstadoCivilDTO>();
			ResultSet rs = null;

			EstadoCivilDTO filaCarreraDTO = null;
			String query = "";
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);

			query = "SELECT * from estados_civiles";
			rs = sqlQueries.consulta(query);
			while (rs.next()) {

				filaCarreraDTO = new EstadoCivilDTO();
				filaCarreraDTO.setId(rs.getInt(1));
				filaCarreraDTO.setDescripcion(rs.getString(2));

				listaEstadoDTO.add(filaCarreraDTO);

			}
			conexionBD.cerrar();
			return listaEstadoDTO;
		} catch (Exception ex) {
			System.out.println("CarreraDAO.retrieveAlmacen:" + ex.getMessage());
			return null;
		}
	}
	public List<GradoDTO> Grado() {

		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;

			List<GradoDTO> listaGradoDTO = new ArrayList<GradoDTO>();
			ResultSet rs = null;

			GradoDTO filaGradoDTO = null;
			String query = "";
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);

			query = "SELECT * from grados";
			rs = sqlQueries.consulta(query);
			while (rs.next()) {

				filaGradoDTO = new GradoDTO();
				filaGradoDTO.setId(rs.getInt(1));
				filaGradoDTO.setDescripcion(rs.getString(2));

				listaGradoDTO.add(filaGradoDTO);

			}
			conexionBD.cerrar();
			return listaGradoDTO;
		} catch (Exception ex) {
			System.out.println("CarreraDAO.retrieveGrado:" + ex.getMessage());
			return null;
		}
	}

	public List<NivelDTO> nivel() {

		try {
			ConexionBD conexionBD = new ConexionBD();
			SQLQueries sqlQueries = null;

			List<NivelDTO> listaEstadoDTO = new ArrayList<NivelDTO>();
			ResultSet rs = null;

			NivelDTO filaCarreraDTO = null;
			String query = "";
			conexionBD.abrir();
			Connection conn = conexionBD.getConexion();
			sqlQueries = new SQLQueries(conn);

			query = "SELECT * from niveles_educativos";
			rs = sqlQueries.consulta(query);
			while (rs.next()) {

				filaCarreraDTO = new NivelDTO();
				filaCarreraDTO.setId(rs.getInt(1));
				filaCarreraDTO.setDescripcion(rs.getString(2));

				listaEstadoDTO.add(filaCarreraDTO);

			}
			conexionBD.cerrar();
			return listaEstadoDTO;
		} catch (Exception ex) {
			System.out.println("CarreraDAO.retrieveAlmacen:" + ex.getMessage());
			return null;
		}
	}

	public List<LicDTO> lic() {
		try {
			String valor = obtenerValorSesion("id");
			int id_session;
			ConexionBD conexionBD = new ConexionBD();
			conexionBD.abrir();

			Connection connection = conexionBD.getConexion();
			PreparedStatement ps = connection.prepareStatement(
					"SELECT  licenciaturas.id_licenciatura, licenciaturas.descripcion FROM vinculaciones  INNER JOIN usuarios ON vinculaciones.usuarios_id_usuarios=usuarios.id_usuarios INNER JOIN roles ON usuarios.roles_id_rol = roles.id_rol INNER JOIN licenciaturas ON vinculaciones.licenciaturas_id_licenciatura=licenciaturas.id_licenciatura WHERE usuarios_id_usuarios =?");
			List<LicDTO> listado = new ArrayList<LicDTO>();
			id_session = Integer.parseInt(valor);
			ps.setInt(1, id_session);
			ResultSet s = ps.executeQuery();

			boolean fo = false;

			while (s.next()) {
				LicDTO licDTO = new LicDTO();
				licDTO.setId(s.getInt("id_licenciatura"));
				licDTO.setDescripcion(s.getString("descripcion"));
				listado.add(licDTO);

				fo = true;
			}
			s.close();
			if (fo) {
				return listado;
			} else {

				return null;
			}

		} catch (Exception e) {
			System.out.println("Error en listarcomisiones()" + e.getMessage());
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
}
